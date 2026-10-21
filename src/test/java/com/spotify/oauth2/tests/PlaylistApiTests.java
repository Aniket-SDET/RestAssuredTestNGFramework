package com.spotify.oauth2.tests;


import com.spotify.oauth2.api.applicationApi.PlaylistApi;
import com.spotify.oauth2.Pojo.errorResponse;
import com.spotify.oauth2.Pojo.playlistRoot;

import com.spotify.oauth2.enums.endPointEnums;
import com.spotify.oauth2.utility.DataLoader;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.spotify.oauth2.utility.FakerUtils.generateDescription;
import static com.spotify.oauth2.utility.FakerUtils.generateName;
import static io.restassured.RestAssured.given;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistApiTests {

    @BeforeMethod()
    public void printThreadId(Method m){
        System.out.println("Method Name :: " +m.getName());
        System.out.println("THREAD ID :: " +Thread.currentThread().getId());
    }


    @Description("This testcase is to verify that a playlist can be created for current user")
    @Test(description = "Create playlist for current user")
    public void createPlaylistCurrentUser(){

        playlistRoot requestPlaylist = requestPlaylistBuilder(generateName(),generateDescription(),false);
        Response response = PlaylistApi.post(requestPlaylist);
        assertStatusCode(response.statusCode(),201);
        assertPlaylistResponse(response.as(playlistRoot.class) , requestPlaylist);

    }

    @Description("This testcase is to verify that playlist details can be fetched using a get request")
    @Test(description = "Get playlist API")
    public void getPlaylist(){

        playlistRoot requestPlaylist = requestPlaylistBuilder("Updated API Playlist 05 - Part 2","Updated playlist by RestAssured API call 02",true);

        Response response = PlaylistApi.get(DataLoader.getInstance().getGetPlaylistId());
        assertStatusCode(response.statusCode(),200);

        assertPlaylistResponse(response.as(playlistRoot.class),requestPlaylist);


    }

    @Description("This testcase is to verify that playlist details can be fetched using a get request for current user")
    @Test(description = "Get current user playlist")
    public void getCurrentUserPlaylist(){

        Response response  = PlaylistApi.get(true);
        assertStatusCode(response.statusCode() , 200);

    }

    @Description("This testcase is to verify that a playlist can be modified using API")
    @Test(description = "Update playlist API")
    public void updatePlaylist(){

        playlistRoot requestPlaylist = requestPlaylistBuilder("Updated API Playlist 4","Updated playlist by Postman API call 6",false);

        Response response = PlaylistApi.put(DataLoader.getInstance().getUpdatePlaylistId(),requestPlaylist);
        assertStatusCode(response.statusCode(),200);


    }

    @Description("This testcase is to verify that a playlist cannot be created without passing the name in payload")
    @Test(description = "Playlist should not be created without passing name in payload")
    public void shouldNotBeAbleToCreateAPlaylistWithoutName(){

        playlistRoot requestPlaylist = requestPlaylistBuilder("",generateDescription(),false);

        Response response = PlaylistApi.post(requestPlaylist);
        assertStatusCode(response.statusCode(),400);

        assertErrorResponse(response.as(errorResponse.class),400,"Missing required field: name");

    }

    @Description("This testcase is to verify that a playlist cannot be created using expired token")
    @Test(description = "User should not be able to create a playlist with an expired token")
    public void shouldNotBeAbleToCreateAPlaylistWithExpiredToken(){

        String invalid_token = "BQAIFiK6x_3NbWAI5eQO1SWE5E-7HJnhdlN22fGLQgoivNNPoDGxW9jHt-kmbjkBww1ymeSM0wKjbUvZ2hsiGMcbT2Ziviza-C69JEoaheKkqcIvkZM99CdjZ5gIJs9JmbfsDibWmNp9VF1kpyx71TjV0esCrFbryOX72fyb0VPT3NcZ3KWPOLnPgRL_LQJvrZ796QP6Vnq2l4QrFn92bdKKEUdXEtonuIOYxQ6W-oAFk8FhIjv_18DfviXpWcDFdCttasF5M9xgIPL_9Q-3FQb_p3su7lK1gZ-PXD-9r_rJVP1khTM2Usf7KgRNHylyRlQ";
        playlistRoot requestPlaylist = requestPlaylistBuilder(generateName(),generateDescription(),false);

        Response response = PlaylistApi.post(requestPlaylist,invalid_token);

        assertErrorResponse(response.as(errorResponse.class),401, "Missing/invalid/expired access token");

    }

    //---Reusable Methods---
    @Step
    public playlistRoot requestPlaylistBuilder(String name, String description, boolean setPublic){

       return playlistRoot.builder()
                .name(name)
                .description(description)
                .isPublic(setPublic)
                .build();

    }

    @Step
    public void assertPlaylistResponse(playlistRoot responsePlaylist , playlistRoot requestPlaylist){

        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.getIsPublic(), equalTo(requestPlaylist.getIsPublic()));

    }

    @Step
    public void assertStatusCode(int actualStatusCode , int expectedStatusCode){

        assertThat(actualStatusCode, equalTo(expectedStatusCode));

    }

    public void assertErrorResponse(errorResponse responseError , int expectedStatus , String expectedMessage){

        assertThat(responseError.getError().getStatus(), equalTo(expectedStatus));
        assertThat(responseError.getError().getMessage(), equalTo(expectedMessage));

    }


}
