package Blacklist.Manager.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    @NotEmpty(message = "name should not be blank")
    private String itemName;

    @NotEmpty(message = "category should not be blank")
    private String category;


}

