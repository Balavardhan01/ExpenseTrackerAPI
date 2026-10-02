package springboot.ExpenseTrackerAPI.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.ExpenseTrackerAPI.DTO.ExpenseRequestDto;
import springboot.ExpenseTrackerAPI.DTO.ExpenseResponseDto;
import springboot.ExpenseTrackerAPI.Service.ExpenseServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    private final ExpenseServiceImpl expenseService;

    // Constructor Injection
    public ExpenseController(ExpenseServiceImpl expenseService) {
        this.expenseService = expenseService;
    }


    @PostMapping
    public ResponseEntity<ExpenseResponseDto> createExpense(@RequestBody ExpenseRequestDto requestDto) {
        // 1. Pass the incoming DTO to the service
        ExpenseResponseDto createdExpense = expenseService.createExpense(requestDto);

        // 2. Return a 201 CREATED status along with the saved data
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExpense);
    }


    @GetMapping
    public ResponseEntity<List<ExpenseResponseDto>> getAllExpenses() {
        // 1. Fetch the list of DTOs from the service
        List<ExpenseResponseDto> expenses = expenseService.getAllExpenses();

        // 2. Return a 200 OK status along with the list
        return ResponseEntity.ok(expenses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseResponseDto> updateExpense(
            @PathVariable Long id,
            @RequestBody ExpenseRequestDto requestDto) {

        ExpenseResponseDto updatedExpense = expenseService.updateExpense(id, requestDto);

        // Return 200 OK with the updated data
        return ResponseEntity.ok(updatedExpense);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {

        expenseService.deleteExpense(id);

        // Return 204 NO CONTENT (Standard REST practice for a successful delete with no return body)
        return ResponseEntity.noContent().build();
    }
}

