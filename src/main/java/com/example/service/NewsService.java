package com.example.service;

import com.example.dto.news.CreatedNewsDto;
import com.example.dto.news.ResponseNewsDto;
import com.example.entity.AttachEntity;
import com.example.entity.NewsEntity;
import com.example.enums.Language;
import com.example.exp.NewsDataNotFoundException;
import com.example.exp.attach.FileNotFoundException;
import com.example.repository.AttachmentRepository;
import com.example.repository.NewsRepository;
import com.example.util.ToDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private final ToDTO toDTO;

    @Autowired
    public NewsService(AttachmentRepository attachmentRepository, ResourceBundleService resourceBundleService, NewsRepository newsRepository, ToDTO toDTO) {
        this.attachmentRepository = attachmentRepository;
        this.resourceBundleService = resourceBundleService;
        this.newsRepository = newsRepository;
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

        Optional<AttachEntity> optional = attachmentRepository.findById(newsDto.getPhotoId());
        if (optional.isEmpty()) {
            throw new FileNotFoundException(resourceBundleService.getMessage("file.not.found", language.name()));
        }

        AttachEntity attachEntity = optional.get();

        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setTitle_uz(newsDto.getTitle_uz());
        newsEntity.setTitle_ru(newsDto.getTitle_ru());
        newsEntity.setDescription_uz(newsDto.getDescription_uz());
        newsEntity.setDescription_ru(newsDto.getDescription_ru());
        newsEntity.setPhotoId(attachEntity);

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
        NewsEntity newsEntity = optional.get();
        ResponseNewsDto response = new ResponseNewsDto();
        response.setId(newsEntity.getId());
        response.setTitle_ru(newsEntity.getTitle_ru());
        response.setDescription_ru(newsEntity.getDescription_ru());
        response.setTitle_uz(newsEntity.getTitle_uz());
        response.setDescription_uz(newsEntity.getDescription_uz());

        response.setPhotoId(newsEntity.getPhotoId().getId());
        return ResponseEntity.ok(response);
    }

    /**
     * This method is used for getting all the news data by paging and sorting
     *
     * @param page int
     * @param size int
     * @return Page
     */
    public ResponseEntity<?> getAllNews(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<NewsEntity> entityPage = newsRepository.findAll(pageable);

        List<ResponseNewsDto> responseNewsDtoList = new ArrayList<>();

        for (NewsEntity newsEntity : entityPage) {
            ResponseNewsDto responseNewsDto = new ResponseNewsDto();

            responseNewsDto.setTitle_uz(newsEntity.getTitle_uz());
            responseNewsDto.setDescription_uz(newsEntity.getDescription_uz());
            responseNewsDto.setPhotoId(newsEntity.getPhotoId().getId());
            responseNewsDto.setTitle_ru(newsEntity.getTitle_ru());
            responseNewsDto.setDescription_ru(newsEntity.getDescription_ru());

            responseNewsDtoList.add(responseNewsDto);
        }


        return ResponseEntity.ok(new PageImpl<>(responseNewsDtoList, pageable, entityPage.getTotalElements()));
    }

    /**
     * This method is used for editing news Data If News Data not found throw NewsDataNotFoundException
     *
     * @param id       Integer
     * @param dto
     * @param language Language
     * @return ResponseNewsDto
     */
    public ResponseEntity<?> editeNews(Integer id, @Valid CreatedNewsDto dto, Language language) {
        Optional<NewsEntity> optional = newsRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NewsDataNotFoundException(resourceBundleService.getMessage("news.not.found", language.name()));
        }


        Optional<AttachEntity> attach = attachmentRepository.findById(dto.getPhotoId());
        if (attach.isEmpty()) {
            throw new FileNotFoundException(resourceBundleService.getMessage("file.not.found", language.name()));
        }


        NewsEntity newsEntity = optional.get();
        newsEntity.setTitle_uz(dto.getTitle_uz());
        newsEntity.setDescription_uz(dto.getDescription_uz());
        newsEntity.setTitle_ru(dto.getTitle_ru());
        newsEntity.setDescription_ru(dto.getDescription_ru());
        newsEntity.setPhotoId(attach.get());

        NewsEntity edited = newsRepository.save(newsEntity);
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

        NewsEntity newsEntity = optional.get();
        AttachEntity attach = newsEntity.getPhotoId();

        try {
            newsRepository.deleteById(id);
            attachmentRepository.delete(attach);
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
