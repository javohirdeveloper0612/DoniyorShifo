package com.example.service;

import com.example.dto.attach.AttachResponseDTO;
import com.example.dto.news.CreatedNewsDto;
import com.example.dto.news.NewsUpdateDTO;
import com.example.dto.news.ResponseNewsDto;
import com.example.entity.NewsEntity;
import com.example.enums.Language;
import com.example.exp.news.NewsDataNotFoundException;
import com.example.repository.AttachmentRepository;
import com.example.repository.NewsRepository;
import com.example.util.ToDTO;
import com.example.util.UrlUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NewsService {


    private final AttachmentRepository attachmentRepository;
    private final ResourceBundleService resourceBundleService;
    private final NewsRepository newsRepository;

    private final AttachService attachService;
    private final ToDTO toDTO;

    @Autowired
    public NewsService(AttachmentRepository attachmentRepository, ResourceBundleService resourceBundleService, NewsRepository newsRepository, AttachService attachService, ToDTO toDTO) {
        this.attachmentRepository = attachmentRepository;
        this.resourceBundleService = resourceBundleService;
        this.newsRepository = newsRepository;
        this.attachService = attachService;
        this.toDTO = toDTO;
    }

    /**
     * This method is used for creating news Data  If News Photo
     *
     * @param newsDto  CreatedNewsDto
     * @param language Language
     * @return ResponseNewsDto
     */

    public ResponseEntity<?> createNews(CreatedNewsDto newsDto, Language language) {


        AttachResponseDTO attachDTO = attachService.uploadFile(newsDto.getFile());

        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setTitle_uz(newsDto.getTitle_uz());
        newsEntity.setTitle_ru(newsDto.getTitle_ru());
        newsEntity.setDescription_uz(newsDto.getDescription_uz());
        newsEntity.setDescription_ru(newsDto.getDescription_ru());
        newsEntity.setPhotoId(attachDTO.getId());

        NewsEntity savedNews = newsRepository.save(newsEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO.responseNewsDto(savedNews));
    }

    /**
     * This method is used for getting news data by id If News data not found throw NewsDataNotFoundException
     *
     * @param id       Integer
     * @param language Language
     * @return ResponseNewsDto
     */
    public ResponseEntity<?> getNewsById(Integer id, Language language) {

        Optional<NewsEntity> optional = newsRepository.findById(id);

        if (optional.isEmpty()) {
            throw new NewsDataNotFoundException(resourceBundleService.getMessage("news.not.found", language.name()));
        }
        NewsEntity entity = optional.get();
        ResponseNewsDto response = new ResponseNewsDto();
        response.setId(entity.getId());
        if (language==Language.RU){
            response.setTitle_ru(entity.getTitle_ru());
            response.setDescription_ru(entity.getDescription_ru());
        }
        if (language==Language.UZ){
            response.setTitle_uz(entity.getTitle_uz());
            response.setDescription_uz(entity.getDescription_uz());
        }
        response.setPhotoUrl(UrlUtil.url+entity.getPhotoId());
        return ResponseEntity.ok(response);
    }

    /**
     * This method is used for getting all the news data by paging and sorting
     *
     * @return Page
     */
    public ResponseEntity<?> getAllNews(Language language) {

        List<NewsEntity> entityPage = newsRepository.findAll();

        List<ResponseNewsDto> responseNewsDtoList = new ArrayList<>();

        for (NewsEntity newsEntity : entityPage) {
            ResponseNewsDto responseNewsDto = new ResponseNewsDto();

            responseNewsDto.setId(newsEntity.getId());
            responseNewsDto.setPhotoUrl(UrlUtil.url+newsEntity.getPhotoId());
            if (language==Language.UZ){
                responseNewsDto.setTitle_uz(newsEntity.getTitle_uz());
                responseNewsDto.setDescription_uz(newsEntity.getDescription_uz());
            }
            if (language==Language.RU){
                responseNewsDto.setTitle_ru(newsEntity.getTitle_ru());
                responseNewsDto.setDescription_ru(newsEntity.getDescription_ru());
            }
            responseNewsDtoList.add(responseNewsDto);
        }


        return ResponseEntity.ok(responseNewsDtoList);
    }

    /**
     * This method is used for editing news Data If News Data not found throw NewsDataNotFoundException
     *
     * @param id       Integer
     * @param dto NewsUpdateDto
     * @param language Language
     * @return ResponseNewsDto
     */
    public ResponseEntity<?> editeNews(Integer id, @Valid NewsUpdateDTO dto, Language language) {
        Optional<NewsEntity> optional = newsRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NewsDataNotFoundException(resourceBundleService.getMessage("news.not.found", language.name()));
        }

        String attachId = optional.get().getPhotoId();
        AttachResponseDTO attachDTO = attachService.uploadFile(dto.getFile());


        NewsEntity newsEntity = optional.get();
        newsEntity.setTitle_uz(dto.getTitle_uz());
        newsEntity.setDescription_uz(dto.getDescription_uz());
        newsEntity.setTitle_ru(dto.getTitle_ru());
        newsEntity.setDescription_ru(dto.getDescription_ru());
        newsEntity.setPhotoId(attachDTO.getId());

        NewsEntity edited = newsRepository.save(newsEntity);

        attachService.deleteById(attachId);

        return ResponseEntity.ok(toDTO.responseNewsDto(edited));
    }

    /**
     * This method is used for deleting News data by id If News Data not found throw NewsDataNotFoundException
     *
     * @param id       Integer
     * @param language Language
     * @return String
     */

    public ResponseEntity<?> deleteNewsById(Integer id, Language language) {

        Optional<NewsEntity> optional = newsRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NewsDataNotFoundException(resourceBundleService.getMessage("news.not.found", language.name()));
        }
        try {
            attachService.deleteById(optional.get().getPhotoId());
            newsRepository.deleteById(id);
        } catch (Exception e) {
            throw new NewsDataNotFoundException(resourceBundleService.getMessage("news.not.found", language.name()));
        }

        if (language.equals(Language.UZ)) {
            return ResponseEntity.ok("Muvaffaqqiyatli o'chirildi");
        } else {
            return ResponseEntity.ok("Удалено успешно");
        }


    }
}
