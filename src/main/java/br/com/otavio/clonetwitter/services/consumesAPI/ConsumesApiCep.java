package br.com.otavio.clonetwitter.services.consumesAPI;

import br.com.otavio.clonetwitter.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsumesApiCep {
    private String apiUrl;

    @Autowired
    private RestTemplate restTemplate;

    public ConsumesApiCep(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String queryCep(String cep){
        apiUrl = "https://viacep.com.br/ws/"+cep+"/json/";

        var cepResponse = restTemplate.getForObject(apiUrl, CepResponse.class);

        if(cepResponse.getLocalidade() == null){
            throw new ResourceNotFoundException("Cep not found: "+ cep);
        }

        return cepResponse.getLocalidade() + ", " + cepResponse.getUf() + ".";
    }

}
