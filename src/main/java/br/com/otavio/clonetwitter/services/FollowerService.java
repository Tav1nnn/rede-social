package br.com.otavio.clonetwitter.services;

import br.com.otavio.clonetwitter.dto.user.UserDto;
import br.com.otavio.clonetwitter.entities.FollowerEntity;
import br.com.otavio.clonetwitter.entities.FollowingEntity;
import br.com.otavio.clonetwitter.entities.UserEntity;
import br.com.otavio.clonetwitter.mapper.UserMapper;
import br.com.otavio.clonetwitter.repositories.FollowerRepository;
import br.com.otavio.clonetwitter.repositories.FollowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowerService {

    @Autowired
    private FollowerRepository followerRepository;
    @Autowired
    private FollowingRepository followingRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    public void newFollow(Long idUserFollow) {
        UserDto userDto = userService.getUser();
        UserDto userDtoFollow = userService.findById(idUserFollow);

        UserEntity userEntity = userMapper.toUserEntity(userDto);
        UserEntity userEntityFollow = userMapper.toUserEntity(userDtoFollow);

        FollowingEntity followingEntity = new FollowingEntity();
        followingEntity.setUserEntity(userEntity);
        followingEntity.setUserFollowingEntity(userEntityFollow);

        followingRepository.save(followingEntity);


        FollowerEntity followerEntity = new FollowerEntity();
        followerEntity.setUserEntity(userEntityFollow);
        followerEntity.setUserFollowerEntity(userEntity);

        followerRepository.save(followerEntity);
    }
}
