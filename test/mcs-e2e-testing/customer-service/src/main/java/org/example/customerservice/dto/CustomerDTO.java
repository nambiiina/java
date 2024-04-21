package org.example.customerservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder @ToString
public class CustomerDTO {
    private Long id;
    @NotEmpty
    @Size(min = 2)
    private String firstName;
    @NotEmpty
    @Size(min = 2)
    private String lastName;
    @NotEmpty
    @Size(min = 8)
    private String email;
}
