package com.example.controller;

import com.example.dto.news.CreatedNewsDto;
import com.example.dto.news.NewsUpdateDTO;
import com.example.enums.Language;
import com.example.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/news")
@Tag(name = "News Controller", description = "This controller for managing news data")
public class NewsController {

    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }


    /**
     * This method is used for creating news Data  If News Photo
     *
     * @param newsDto CreatedNewsDto
     * @return ResponseNewsDto
     */

    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping(value = "/create",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Create News data method", description = "This method is used for creating news data")
    public ResponseEntity<?> createNews(@Valid @ModelAttribute CreatedNewsDto newsDto,
                                        @RequestHeader(name = "Accept-Language", defaultValue = "UZ") Language language) {
        log.info("create news: title_uz {} ,title_ru {}", newsDto.getTitle_uz(), newsDto.getDescription_uz());
        return newsService.createNews(newsDto, language);

    }

    /**
     * This method is used for getting news data by id If News data not found throw NewsDataNotFoundException
     *
     * @param id Integer
     * @return ResponseNewsDto
     */

    @GetMapping("/public/view_news/{id}")
    @Operation(summary = "Getting News data By id method", description = "This method is used for getting News Data By Id")
    public ResponseEntity<?> getNewsById(@PathVariable Integer id,
                                         @RequestHeader(name = "Accept-Language") Language language) {
        log.info(" get news by id : newsId {} ", id);
        return newsService.getNewsById(id, language);
    }

    /**
     * This method is used for getting all the news data by paging and sorting
     *

     * @return Page
     */
    @GetMapping("/public/view_all_news")
    @Operation(summary = "Getting All the news data method", description = "This method is used for viewing all the news data by paging ")
    public ResponseEntity<?> getAllNews(@RequestHeader(name = "Accept-Language", defaultValue = "UZ") Language language) {

        return newsService.getAllNews(language);
    }

    /**
     * This method is used for editing news Data If News Data not found throw NewsDataNotFoundException
     *
     * @param id Integer
     * @return ResponseNewsDto
     */

    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping(value = "/edite/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Edite News data", description = "This method is used for editing news Data If News Data not found throw NewsDataNotFoundException")
    public ResponseEntity<?> editeNews(@PathVariable Integer id, @Valid
    @ModelAttribute NewsUpdateDTO updateDTO, @RequestHeader(name = "Accept-Language", defaultValue = "UZ") Language language) {
        log.info(" editing news : newsId {} , title_uz {} ,title_ru {}", id, updateDTO.getTitle_uz(), updateDTO.getDescription_uz());
        return newsService.editeNews(id, updateDTO, language);
    }

    /**
     * This method is used for deleting News data by id If News Data not found throw NewsDataNotFoundException
     *
     * @param id Integer
     * @return String
     */


    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete news data method", description = "This method is used for deleting news data")
    public ResponseEntity<?> deleteNews(@PathVariable Integer id, @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language) {
        log.info(" get news by id : newsId {} ", id);
        return newsService.deleteNewsById(id, language);

    }

}
