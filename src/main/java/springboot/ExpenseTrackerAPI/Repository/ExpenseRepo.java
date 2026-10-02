package springboot.ExpenseTrackerAPI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.ExpenseTrackerAPI.Entity.Expense;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense,Long> {
}
