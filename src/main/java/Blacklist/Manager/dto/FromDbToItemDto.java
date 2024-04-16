package Blacklist.Manager.dto;

import Blacklist.Manager.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FromDbToItemDto {
    private Long id;

    private String name;

    private String category;

    public FromDbToItemDto(Item item){
        id = item.getId();
        this.name = item.getItemName();
        this.category = item.getItemCategory().getCategoryName();
    }


    public FromDbToItemDto(List<Item> items) {
    }
}
