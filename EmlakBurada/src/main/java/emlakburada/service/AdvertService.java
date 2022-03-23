package emlakburada.service;

import emlakburada.Constant.Messages;
import emlakburada.core.*;
import emlakburada.dto.request.AdvertRequest;
import emlakburada.dto.response.AdvertResponse;
import emlakburada.enums.AdvertStatus;
import emlakburada.model.Advert;
import emlakburada.model.User;
import emlakburada.repository.AdvertRepository;
import emlakburada.repository.UserRepository;
import emlakburada.service.BaseService.AdvertBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdvertService extends AdvertBaseService {

    @Autowired
    private AdvertRepository advertRepository;

    @Autowired
    private UserRepository userRepository;

    public Result saveAdvert(AdvertRequest advertRequest){

        Optional<User> foundUser = userRepository.findById(advertRequest.getUserId());
        User foundUser2 = userRepository.getUserById(advertRequest.getUserId());

        if(foundUser2.getNumberOfAdverts() == 0){
            return new ErrorResult(Messages.advertRightExpired);
        }
        checkAdvertNumberGreaterThanZero(foundUser2.getId());

        if(!checkAdvertNumberGreaterThanZero(advertRequest.getUserId())){
            return new ErrorResult(Messages.advertIsNotSaved);
        }

        DataResult<Advert> advert = convertToAdvertEntity(advertRequest, foundUser);

        if(advert == null){
            return new ErrorResult(Messages.advertIsNotSaved);
        }

        advertRepository.save(advert.getData());


        Integer quantity = foundUser2.getNumberOfAdverts();
        quantity--;
        foundUser2.setNumberOfAdverts(quantity);
        userRepository.save(foundUser2);

        return new SuccessResult(Messages.advertSaved);

    }

    public boolean checkAdvertNumberGreaterThanZero(Integer userId){
        User user = userRepository.getUserById(userId);
        if(user.getNumberOfAdverts() == 0){
            return false;
        }
        return true;
    }

    public DataResult<List<AdvertResponse>> getAllAdverts(){

        List<Advert> adverts = advertRepository.findAll();

        List<Advert> activeAdverts = new ArrayList<>();

        for(Advert advert : adverts){
            if(advert.getAdvertStatus() == AdvertStatus.ACTIVE){
                activeAdverts.add(advert);
            }
        }

        return new SuccessDataResult<>
                (activeAdverts.stream()
                        .map(advert -> convertToAdvertResponse(advert))
                        .collect(Collectors.toList()), Messages.allAdvertsListed);

    }


    public DataResult<AdvertResponse> getAdvertByAdvertNo(Integer advertNo){

        Advert advert = advertRepository.getAdvertByAdvertNo(advertNo);

        if(advert != null){
            return new SuccessDataResult<AdvertResponse>(convertToAdvertResponse(advert), Messages.advertListed);
        }
        return new ErrorDataResult<>(Messages.advertIsNotFound);

    }

    public DataResult<List<AdvertResponse>> getActiveAdvertsByUserId(Integer userId){

        User user = userRepository.getUserById(userId);
        List<Advert> adverts = user.getAdvertList();

        return new SuccessDataResult<List<AdvertResponse>>
                (adverts.stream()
                        .filter(advert -> advert.getAdvertStatus() == AdvertStatus.ACTIVE)
                        .map(advert -> convertToAdvertResponse(advert))
                        .collect(Collectors.toList()), Messages.advertsListedByUser);
    }

    public DataResult<List<AdvertResponse>> getPassiveAdvertsByUserId(Integer userId){

        User user = userRepository.getUserById(userId);
        List<Advert> adverts = user.getAdvertList();

        return new SuccessDataResult<List<AdvertResponse>>
                (adverts.stream()
                        .filter(advert -> advert.getAdvertStatus() == AdvertStatus.PASSIVE)
                        .map(advert -> convertToAdvertResponse(advert))
                        .collect(Collectors.toList()), Messages.userPassiveAdvertsListed);
    }

    public DataResult<List<AdvertResponse>> getAdvertsByUserId(Integer userId){

        User user = userRepository.getUserById(userId);
        List<Advert> adverts = user.getAdvertList();

        return new SuccessDataResult<List<AdvertResponse>>
                (adverts.stream()
                        .filter(advert -> advert.getAdvertStatus() == AdvertStatus.ACTIVE)
                        .map(advert -> convertToAdvertResponse(advert))
                        .collect(Collectors.toList()), Messages.advertsListedByUser);
    }

    public Result updateAdvertById(Integer advertId,AdvertRequest advertRequest){

        Advert advert = advertRepository.getAdvertById(advertId);

        if(advert != null){
            advert.setTitle(advertRequest.getTitle());
            advert.setPrice(advertRequest.getPrice());
            advert.setDescription(advertRequest.getDescription());
            advert.setRealEstateType(advertRequest.getRealEstateType());
            advert.setAdvertStatus(advertRequest.getAdvertStatus());


            advertRepository.save(advert);

            return new SuccessResult(Messages.advertUpdated);
        }

        return new ErrorResult(Messages.advertCouldNotUpdated);

    }

    public Result deleteAdvertById(Integer advertId){
        Advert advert = advertRepository.getAdvertById(advertId);

        if(advert != null){
            advertRepository.delete(advert);
            return new SuccessResult(Messages.advertDeleted);
        }

        return new ErrorResult(Messages.advertIsNotFound);

    }



}
