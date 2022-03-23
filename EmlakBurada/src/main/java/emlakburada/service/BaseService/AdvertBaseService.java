package emlakburada.service.BaseService;

import emlakburada.Constant.Messages;
import emlakburada.core.DataResult;
import emlakburada.core.ErrorDataResult;
import emlakburada.core.SuccessDataResult;
import emlakburada.dto.request.AdvertRequest;
import emlakburada.dto.response.AdvertResponse;
import emlakburada.model.Advert;
import emlakburada.model.User;

import java.util.Optional;

public class AdvertBaseService {

    private static int advertNo = 1717170;

    protected DataResult<Advert> convertToAdvertEntity(AdvertRequest advertRequest, Optional<User> foundUser){

        Advert advert = null;

        if(foundUser.isPresent()){
            advert = new Advert();
            advert.setCreatorUser(foundUser.get());

            advertNo++;

            advert.setAdvertNo(advertNo);
            advert.setTitle(advertRequest.getTitle());
            advert.setPrice(advertRequest.getPrice());
            advert.setDescription(advertRequest.getDescription());
            advert.setRealEstateType(advertRequest.getRealEstateType());
            return new SuccessDataResult<>(advert);

        }else{
            return new ErrorDataResult<>(Messages.userNotFound);
        }

    }

    public AdvertResponse convertToAdvertResponse(Advert savedAdvert){
        AdvertResponse advertResponse = new AdvertResponse();
        advertResponse.setAdvertNo(savedAdvert.getAdvertNo());
        advertResponse.setTitle(savedAdvert.getTitle());
        advertResponse.setDescription(savedAdvert.getDescription());
        //advertResponse.setUser(savedAdvert.getCreatorUser());

        advertResponse.setUserId(savedAdvert.getCreatorUser().getId());
        advertResponse.setRealEstateType(savedAdvert.getRealEstateType());
        advertResponse.setPrice(savedAdvert.getPrice());
        advertResponse.setCreatedDate(savedAdvert.getCreatedDate());
        advertResponse.setAdvertStatus(savedAdvert.getAdvertStatus());

        return advertResponse;



    }



}
