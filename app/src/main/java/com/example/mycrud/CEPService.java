package com.example.mycrud;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CEPService {

    @GET("{cep}/json")
    Call<CEP> consultCEP(@Path("cep")String cep);

}
