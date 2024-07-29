package com.tinqinacademy.comments.rest.configurations;

public class RestApiRoutes {
    public static final String API = "/api/v1";
    public static final String API_HOTEL = API + "/hotel";
    public static final String API_SYSTEM = API + "/system";

    private static final String COMMENT = "/comment";

    public static final String GET_ALL_COMMENTS = API_HOTEL + "/{roomId}" + COMMENT;
    public static final String ADD_COMMENT = API_HOTEL + "/{roomId}" + COMMENT;
    public static final String EDIT_COMMENT = API_HOTEL + COMMENT + "/{commentId}";

    public static final String EDIT_COMMENT_ADMIN = API_SYSTEM + COMMENT + "/{commentId}";
    public static final String DELETE_COMMENT_ADMIN = API_SYSTEM + COMMENT + "/{commentId}";
}
