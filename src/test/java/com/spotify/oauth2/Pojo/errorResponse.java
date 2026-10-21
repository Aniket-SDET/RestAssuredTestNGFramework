package com.spotify.oauth2.Pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class errorResponse {

  @JsonProperty("error")
  private error error;

  public errorResponse(){

  }

  @JsonProperty("error")
    public void setError(error error) {
        this.error = error;
    }

  @JsonProperty("error")
  public error getError() {
    return error;
  }
}
