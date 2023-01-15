package com.example.controller;

import com.example.dto.news.CreatedNewsDto;
import com.example.enums.Language;
import com.example.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
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
     * @param newsDto  CreatedNewsDto
     * @param language Language
     * @return ResponseNewsDto
     */

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    @Operation(summary = "Create News data method", description = "This method is used for creating news data")
    public ResponseEntity<?> createNews(@Valid @RequestBody CreatedNewsDto newsDto,
                                        @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language) {
        return newsService.createNews(newsDto, language);

    }

    /**
     * This method is used for getting news data by id If News data not found throw NewsDataNotFoundException
     *
     * @param id       Integer
     * @param language Language
     * @return ResponseNewsDto
     */

    @GetMapping("/public/view_news/{id}")
    @Operation(summary = "Getting News data By id method", description = "This method is used for getting News Data By Id")
    public ResponseEntity<?> getNewsById(@PathVariable Integer id, @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language) {
        return newsService.getNewsById(id, language);
    }

    /**
     * This method is used for getting all the news data by paging and sorting
     *
     * @param page     int
     * @param size     int
     * @param language Language
     * @return Page
     */
    @GetMapping("/public/view_all_news")
    @Operation(summary = "Getting All the news data method", description = "This method is used for viewing all the news data by paging ")
    public ResponseEntity<?> getAllNews(@RequestParam int page, @RequestParam int size, @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language) {
        return newsService.getAllNews(page, size, language);
    }

    /**
     * This method is used for editing news Data If News Data not found throw NewsDataNotFoundException
     *
     * @param id       Integer
     * @param language Language
     * @return ResponseNewsDto
     */

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/edite/{id}")
    @Operation(summary = "Edite News data", description = "This method is used for editing news Data If News Data not found throw NewsDataNotFoundException")
    public ResponseEntity<?> editeNews(@PathVariable Integer id, @Valid @RequestBody CreatedNewsDto dto, @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language) {
        return newsService.editeNews(id, dto, language);
    }

    /**
     * This method is used for deleting News data by id If News Data not found throw NewsDataNotFoundException
     *
     * @param id       Integer
     * @param language Language
     * @return String
     */


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete news data method", description = "This method is used for deleting news data")
    public ResponseEntity<?> deleteNews(@PathVariable Integer id, @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language) {
        return newsService.deleteNewsById(id, language);

    }

}
