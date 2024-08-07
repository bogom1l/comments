package com.tinqinacademy.restexport;

import com.tinqinacademy.comments.api.operations.addcomment.AddCommentInput;
import com.tinqinacademy.comments.api.operations.editcomment.EditCommentInput;
import com.tinqinacademy.comments.api.operations.editcommentadmin.EditCommentAdminInput;
import com.tinqinacademy.comments.api.restroutes.RestApiRoutes;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@FeignClient(name = "comments", url = "http://localhost:8080")
@FeignClient(name = "comments")
@Headers("Content-Type: application/json")
public interface CommentsRestExportClient {

    @GetMapping(RestApiRoutes.GET_ALL_COMMENTS)
    ResponseEntity<?> getAllComments(@PathVariable String roomId);

    @PostMapping(RestApiRoutes.ADD_COMMENT)
    ResponseEntity<?> addComment(@PathVariable String roomId,
                                 @RequestBody AddCommentInput input);

    @PutMapping(RestApiRoutes.EDIT_COMMENT)
    ResponseEntity<?> editComment(@PathVariable String commentId,
                                  @RequestBody EditCommentInput input);

    @PatchMapping(RestApiRoutes.EDIT_COMMENT_ADMIN)
    ResponseEntity<?> editCommentAdmin(@PathVariable String commentId,
                                       @RequestBody EditCommentAdminInput input);

    @DeleteMapping(RestApiRoutes.DELETE_COMMENT_ADMIN)
    ResponseEntity<?> deleteCommentAdmin(@PathVariable String commentId);
}
