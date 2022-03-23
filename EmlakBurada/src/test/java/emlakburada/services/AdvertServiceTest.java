package emlakburada.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import emlakburada.Constant.Messages;
import emlakburada.core.DataResult;
import emlakburada.core.Result;
import emlakburada.dto.request.AdvertRequest;
import emlakburada.dto.response.AdvertResponse;
import emlakburada.enums.AdvertStatus;
import emlakburada.enums.UserType;
import emlakburada.model.Advert;
import emlakburada.model.User;
import emlakburada.repository.AdvertRepository;
import emlakburada.repository.UserRepository;
import emlakburada.service.AdvertService;
import org.aspectj.bridge.Message;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class AdvertServiceTest {

    @InjectMocks
    private AdvertService advertService;

    @Mock
    private AdvertRepository advertRepository;

    @Mock
    private UserRepository userRepository;


//    @Test
//    void getActiveAdvertByAdvertNo(){
//
//        Advert advert = prepareAdvert("Satılık");
//
//        Mockito
//                .when(advertRepository.getAdvertByAdvertNo(advert.getAdvertNo()))
//                .thenReturn(advert);
//
//
//        DataResult<AdvertResponse> result = advertService.getAdvertByAdvertNo(1);
//
//        assertNotNull(result);
//        assertEquals("Advert listed!",result.getMessage());
//
//
//    }


    @Test
    void updateAdvertById(){

        Advert advert = prepareAdvert("Satılık");

        Mockito
                .when(advertRepository.getAdvertById(advert.getId()))
                .thenReturn(advert);

        Mockito
                .when(advertRepository.save(advert))
                .thenReturn(any());

        Result result = advertService.updateAdvertById(advert.getId(),prepareAdvertRequest());

        assertEquals(result.getMessage(),Messages.advertUpdated);
        assertEquals(true,result.isSuccess());

    }


    @Test
    void deleteAdvertById(){
        Advert advert = prepareAdvert("Satılık");


        Mockito
                .when(advertRepository.getAdvertById(advert.getId()))
                .thenReturn(advert);

        Result  result = advertService.deleteAdvertById(advert.getId());

        assertEquals(result.getMessage(), Messages.advertDeleted);
        assertEquals(true,result.isSuccess());


    }


    @Test
    void getAllAdverts(){

        Mockito
                .when(advertRepository.findAll())
                .thenReturn(prepareMockAdvertList());

        DataResult<List<AdvertResponse>> advertList = advertService.getAllAdverts();

        assertNotEquals(0,advertList.getData().size());

        assertEquals(1,advertList.getData().get(0).getAdvertNo());

    }

    private List<Advert> prepareMockAdvertList() {
        List<Advert> adverts = new ArrayList<>();
        Advert advert = prepareAdvert("acil satılık");
        Advert advert1 = prepareAdvert("Sahibinden satılık");
        adverts.add(advert);
        adverts.add(advert1);
        return adverts;
    }

    private Advert prepareAdvert(String description){
        Advert advert = new Advert();
        advert.setId(1);
        advert.setAdvertNo(1);
        advert.setDescription(description);
        advert.setAdvertStatus(AdvertStatus.ACTIVE);
        advert.setCreatorUser(prepareUser());

        return advert;
    }

    private User prepareUser(){
        User user = new User();
        user.setUserType(UserType.INDIVIDUAL);
        user.setName("alperen");
        user.setEmail("alperen@alperen.com");
        user.setId(1);
        return user;
    }

    private AdvertRequest prepareAdvertRequest(){
        AdvertRequest advertRequest = new AdvertRequest();
        advertRequest.setAdvertStatus(AdvertStatus.ACTIVE);
        advertRequest.setDescription("Satılık");
        advertRequest.setUserId(prepareUser().getId());
        return advertRequest;

    }

}
