package springboot.ExpenseTrackerAPI.DTO;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
public class ExpenseRequestDto {

    private String title;
    private BigDecimal amount;
    private String category;

}
