package org.java.financespring.service;

import org.java.financespring.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
public class PayrollCalculatorService {

    private final PayrollSetting payrollSetting;

    public PayrollCalculatorService(PayrollSetting payrollSetting) {
        this.payrollSetting = payrollSetting;
    }

    public Payroll calculatePayroll(Employee employee,
                                    List<Attendance> attendances,
                                    SalaryStructure salaryStructure,
                                    List<Bonus> bonuses,
                                    List<Deduction> deductions,
                                    LocalDate periodStart,
                                    LocalDate periodEnd) {

        BigDecimal baseSalary = salaryStructure.getBaseSalary();

        // مزایای اضافی
        BigDecimal totalAllowances = salaryStructure.getHousingAllowance()
                .add(salaryStructure.getChildrenAllowance())
                .add(salaryStructure.getTransportationAllowance())
                .add(salaryStructure.getMealAllowance());

        // اضافه‌کاری
        double totalOvertimeHours = attendances.stream()
                .filter(a -> !a.isAbsent())
                .mapToDouble(Attendance::getOvertimeHours)
                .sum();

        BigDecimal hourlyRate = baseSalary
                .divide(BigDecimal.valueOf(payrollSetting.getStandardMonthlyHours()), 2, RoundingMode.HALF_UP);

        BigDecimal overtimePay = BigDecimal.valueOf(totalOvertimeHours)
                .multiply(hourlyRate)
                .multiply(payrollSetting.getOvertimeRate());

        // جریمه تأخیر
        double totalDelayMinutes = attendances.stream()
                .mapToDouble(Attendance::getDelayMinutes)
                .sum();

        BigDecimal delayPenalty = BigDecimal.valueOf(totalDelayMinutes)
                .divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_UP)
                .multiply(hourlyRate);

        // مجموع پاداش‌ها
        BigDecimal totalBonuses = bonuses.stream()
                .map(Bonus::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // مجموع کسورات
        BigDecimal totalDeductions = deductions.stream()
                .map(Deduction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // محاسبه پایه مالیات
        BigDecimal taxBase = baseSalary.add(overtimePay).add(totalAllowances);
        if (payrollSetting.isIncludeBonusesInTax()) {
            taxBase = taxBase.add(totalBonuses);
        }

        BigDecimal tax = taxBase
                .multiply(payrollSetting.getTaxRate())
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

        // محاسبه بیمه
        BigDecimal insurance = baseSalary
                .multiply(payrollSetting.getInsuranceRate())
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

        // حقوق خالص
        BigDecimal netSalary = baseSalary
                .add(totalAllowances)
                .add(overtimePay)
                .add(totalBonuses)
                .subtract(totalDeductions)
                .subtract(delayPenalty)
                .subtract(tax)
                .subtract(insurance);

        // ساختن  فیش حقوقی
        Payroll payroll = new Payroll();
        payroll.setEmployee(employee);
        payroll.setPayPeriodStart(periodStart);
        payroll.setPayPeriodEnd(periodEnd);
        payroll.setBaseSalary(baseSalary);
        payroll.setOvertimePay(overtimePay);
        payroll.setAllowances(totalAllowances);
        payroll.setTaxWithheld(tax);
        payroll.setInsuranceWithheld(insurance);
        payroll.setDelayPenalty(delayPenalty);
        payroll.setNetSalary(netSalary);
        payroll.setCreatedDate(LocalDate.now());
        payroll.setLastUpdated(LocalDate.now());

        return payroll;
    }
}