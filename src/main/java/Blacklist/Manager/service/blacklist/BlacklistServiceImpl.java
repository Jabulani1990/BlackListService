package Blacklist.Manager.service.blacklist;

import Blacklist.Manager.dto.AppResponse;
import Blacklist.Manager.dto.BlacklistDTO;
import Blacklist.Manager.dto.FromDbToBlackListDto;
import Blacklist.Manager.entity.Blacklist;
import Blacklist.Manager.entity.Item;
import Blacklist.Manager.exception.ApiException;
import Blacklist.Manager.repository.BlacklistRepository;
import Blacklist.Manager.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlacklistServiceImpl implements BlacklistService{

    private final BlacklistRepository blacklistRepository;

    private final ItemRepository itemRepository;



    @Override
    public AppResponse<Map<String, Object>> getAllBlacklistedItems(Pageable pageable) {
        Page<FromDbToBlackListDto> dbToBlackListDtoPage = blacklistRepository.findAll(pageable).map(FromDbToBlackListDto::new);

        Map<String, Object> page = Map.of(
                "page", dbToBlackListDtoPage.getNumber(),
                "totalPages", dbToBlackListDtoPage.getTotalPages(),
                "totalElements", dbToBlackListDtoPage.getTotalElements(),
                "size", dbToBlackListDtoPage. getSize(),
                "content", dbToBlackListDtoPage.getContent()
        );

        return new AppResponse<>("successful", page);
    }

    @Override
    public AppResponse<String> addBlacklistedItem(BlacklistDTO blacklistDTO) {
        Item item = itemRepository.findByItemName(blacklistDTO.getItemName())
                .orElseThrow(() -> new ApiException("Item not found"));


        boolean existingBlacklist = blacklistRepository.existsByItem(item.getItemName());
        if (existingBlacklist) {
            return new AppResponse<>(0, "item already blacklisted");
        }

        addToBlacklist(blacklistDTO, item);

        return new AppResponse<>("successfully added too the blacklist");
    }

    @Override
    public AppResponse<String> removeBlacklistedItem(BlacklistDTO blacklistDTO) {

        Blacklist blacklist = blacklistRepository.findByItem(blacklistDTO.getItemName()).
                orElseThrow(() -> new ApiException("item not found"));

        blacklistRepository.delete(blacklist);

        addToItem(blacklistDTO.getItemName());

        return new AppResponse<>(0,"successfully remove from blacklist");
    }

    @Override
     public AppResponse<Blacklist> updateBlacklist(long id, BlacklistDTO blacklistDTO) {
        Blacklist existingBlacklist = blacklistRepository.findById(id)
                .orElseThrow(() -> new ApiException("Blacklist not found"));

        BeanUtils.copyProperties(blacklistDTO, existingBlacklist);
        Blacklist updatedBlacklist = blacklistRepository.save(existingBlacklist);

        return new AppResponse<>("successfully updated", updatedBlacklist);
    }

    public void addToBlacklist(BlacklistDTO blacklistDTO, Item item) {
        Blacklist blacklist = new Blacklist();
        blacklist.setItem(blacklistDTO.getItemName());
        blacklist.setCategory(item.getItemCategory().getCategoryName());

        blacklist.setReason(blacklistDTO.getReason());
        blacklist.setCreatedAt(LocalDateTime.now());
        blacklist.setUpdatedAt(LocalDateTime.now());

        blacklistRepository.save(blacklist);

        item.setDeleted(true);
        itemRepository.save(item);
    }

    public void addToItem(String itemName){

        Item item = itemRepository.findByItemName(itemName)
            .orElseThrow(() -> new ApiException("Item not found"));

        item.setDeleted(false);

        itemRepository.save(item);
    }

}