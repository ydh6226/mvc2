package hello.file.web;

import hello.file.domain.Item;
import hello.file.domain.ItemForm;
import hello.file.domain.ItemRepository;
import hello.file.domain.UploadFile;
import hello.file.file.FileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final FileStore fileStore;

    @PostMapping("/items/save")
    public String saveItem(@ModelAttribute ItemForm itemForm) throws IOException {
        UploadFile attachFile = fileStore.storeFile(itemForm.getAttachFile());
        List<UploadFile> storeImageFiles = fileStore.storeFiles(itemForm.getImageFiles());

        //DB에 저장
        Item item = new Item(itemForm.getItemName(), attachFile, storeImageFiles);
        itemRepository.save(item);
        return item.getItemName();
    }

    @GetMapping(path = "/items/{id}")
    public Item items(@PathVariable Long id) {
        return itemRepository.findById(id);
    }

    @GetMapping(value = "/images/{fileName}", produces = IMAGE_PNG_VALUE)
    public Resource downloadImage(@PathVariable String fileName) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(fileName));
    }

    @GetMapping(value = "/attach/{itemId}", produces = IMAGE_PNG_VALUE)
    public ResponseEntity<Resource> downloadAttach(@PathVariable Long itemId) throws MalformedURLException {
        Item item = itemRepository.findById(itemId);
        String storeFileName = item.getAttachFile().getStoreFileName();
        String uploadFileName = item.getAttachFile().getUploadFileName();
        UrlResource urlResource = new UrlResource("file:" + fileStore.getFullPath(storeFileName));
        log.info("uploadFileName={}", uploadFileName);

        String encodedUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(urlResource);
    }
}
