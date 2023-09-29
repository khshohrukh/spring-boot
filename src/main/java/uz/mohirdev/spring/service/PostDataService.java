package uz.mohirdev.spring.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.mohirdev.spring.model.Post;
import uz.mohirdev.spring.entity.PostData;
import uz.mohirdev.spring.repository.PostDataRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostDataService {

    private final PostDataRepository postDataRepository;

    public PostDataService(PostDataRepository postDataRepository) {
        this.postDataRepository = postDataRepository;
    }

    public PostData save(PostData postData){
        return postDataRepository.save(postData);
    }

    public List<PostData> saveAll(Post[] posts){
        List<PostData> postDataList = new ArrayList<>();
        for (Post post:posts){
            PostData postData = new PostData();
            postData.setPostId(post.getId());
            postData.setUserId(post.getUserId());
            postData.setTitle(post.getTitle());
            postData.setBody(post.getBody());
            postDataList.add(postData);
        }
        return postDataRepository.saveAll(postDataList);
    }

    @Transactional(readOnly = true)
    public Page<PostData> findAll(Pageable pageable){
        return postDataRepository.findAll(pageable);
    }
}
