package ru.netology.repository;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;



@Component

// Stub
public class PostRepository {
  private final ConcurrentHashMap<Long, Post> postRepo = new ConcurrentHashMap<>();
  private final AtomicLong idCounter = new AtomicLong(1);
  public List<Post> all() {

    //Доработать
    //return Collections.emptyList();
    return new ArrayList<>(postRepo.values());
  }

  public Optional<Post> getById(long id) {
    return Optional.of(postRepo.get(id));
  }

  public Post save(Post post) throws Exception {
    //Доработать
    //return post;
    long postID = post.getId();
    if (postID == 0) {
      long newPostId = idCounter.getAndIncrement();
      post.setId(newPostId);
      postRepo.put(newPostId,post);
    } else {
      if (postRepo.containsKey(postID)) {
        throw new  Exception("Error write!!! Such ID already occupied");
      }
      postRepo.put(postID,post);
    }
    return post;
  }

  public void removeById(long id) {
    postRepo.remove(id);
  }
}
