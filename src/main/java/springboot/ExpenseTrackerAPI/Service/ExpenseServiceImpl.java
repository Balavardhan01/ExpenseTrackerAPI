package springboot.ExpenseTrackerAPI.Service;

import org.springframework.stereotype.Service;
import springboot.ExpenseTrackerAPI.DTO.ExpenseRequestDto;
import springboot.ExpenseTrackerAPI.DTO.ExpenseResponseDto;
import springboot.ExpenseTrackerAPI.Entity.Expense;
import springboot.ExpenseTrackerAPI.Entity.ExpenseCategory;
import springboot.ExpenseTrackerAPI.Repository.ExpenseRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl {

    private final ExpenseRepo repo;

    // Constructor Injection (Best Practice)
    public ExpenseServiceImpl(ExpenseRepo expenseRepository) {
        this.repo = expenseRepository;
    }

    public ExpenseResponseDto createExpense(ExpenseRequestDto requestDto) {
        // 1. Manual Mapping: DTO -> Entity
        Expense expense = new Expense();
        expense.setTitle(requestDto.getTitle());
        expense.setAmount(requestDto.getAmount());
        expense.setCategory(ExpenseCategory.valueOf(requestDto.getCategory().toUpperCase()));
        expense.setExpenseDate(LocalDate.now()); // Set current date automatically

        // 2. Save to Database
        Expense savedExpense = repo.save(expense);

        // 3. Manual Mapping: Entity -> DTO
        return mapToResponseDto(savedExpense);
    }

    public List<ExpenseResponseDto> getAllExpenses() {
        List<Expense> expenses = repo.findAll();

        // Convert list of Entities to list of DTOs
        return expenses.stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    // Helper method to keep your code clean
    private ExpenseResponseDto mapToResponseDto(Expense expense) {
        ExpenseResponseDto responseDto = new ExpenseResponseDto();
        responseDto.setId(expense.getId());
        responseDto.setTitle(expense.getTitle());
        responseDto.setAmount(expense.getAmount());
        responseDto.setCategory(expense.getCategory().name());
        responseDto.setExpenseDate(expense.getExpenseDate());
        return responseDto;
    }
}
