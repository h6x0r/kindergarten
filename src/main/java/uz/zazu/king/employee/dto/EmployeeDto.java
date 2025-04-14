package uz.zazu.king.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.zazu.king.employee.common.enums.EmployeeState;
import uz.zazu.king.employee.common.enums.Position;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private String id;
    private String fullName;
    private Set<Position> positions;
    private LocalDateTime hireDate;
    private EmployeeState status;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}