package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.util.*;


@org.springframework.stereotype.Service
public class Service {

    private static String url="https://api.github.com/search/repositories?q=created:>2020-09-05&sort=stars&order=desc&page=";
    private int i;

   @Autowired
    private RestTemplate restTemplate;



    private Response getRepos()  {
        Response response=sendRequest("1");
        Response response2=sendRequest("2");
        Response response3=sendRequest("3");
        response2.getItems().forEach(item-> response.getItems().add(item));
        response3.getItems().forEach(item-> response.getItems().add(item));
        return response;
}

    private Response sendRequest(String pageNumber){
        HttpHeaders headers=new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity =new HttpEntity<>("parameters",headers);
        ResponseEntity<Response> response=restTemplate.exchange(url+pageNumber, HttpMethod.GET, entity, new ParameterizedTypeReference<Response>() {
        });
    return response.getBody();
}

    private Set<String> languages(){
        List<String> list=new ArrayList<>();
        getRepos().getItems().forEach(item->list.add(item.getLanguage()));
        Set<String> set = new HashSet<>(list);
        list.clear();
        list.addAll(set);
        return set;
    }

    public List<ResponseApi> getResponseApi(){
        List<Data> response=getRepos().getItems();
        List<ResponseApi> responseApis=new ArrayList<>();
        languages().forEach(l->{
            List<Data> data=new ArrayList<>();
            ResponseApi res=new ResponseApi();
            i=0;
            res.setLanguage(l);
            response.forEach(item->{
                if(item.getLanguage()!=null&&item.getLanguage().equals(l)){
                    i++;
                    res.setOccurence(i);
                    data.add(item);
                }else if(item.getLanguage()==l){
                    i++;
                    res.setOccurence(i);
                    data.add(item);
                }
            });
            res.setRepos(data);
            responseApis.add(res);
        });
        return responseApis;
    }
}
