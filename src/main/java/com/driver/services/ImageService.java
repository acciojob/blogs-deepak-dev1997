package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        Blog blog=blogRepository2.findById(blogId).get();
        Image image=new Image();

        image.setDescription(description);
        image.setDimensions(dimensions);

        image.setBlog(blog);

        blog.getImageList().add(image);
        blogRepository2.save(blog);
        return image;




    }

    public void deleteImage(Integer id){
    imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        StringBuilder st=new StringBuilder();
        int ch1=-1;
        int ch2=-1;

        for(int a=0;a<screenDimensions.length();a++) {
            if (screenDimensions.charAt(a) == 'X') {
                if (ch1 == -1) ch1 = Integer.parseInt(st.toString());
                else ch2 = Integer.parseInt(st.toString());
                st = new StringBuilder();

            } else {
                st.append(screenDimensions.charAt(a));
            }
        }

            Image image=imageRepository2.findById(id).get();
            st=new StringBuilder();
            String temp=image.getDimensions();
            int c=-1;
            int b=-1;

            for(int a=0;a<temp.length();a++){
                if(temp.charAt(a)=='X')
                {
                    if(c==-1) a=Integer.parseInt(st.toString());
                    else b=Integer.parseInt(st.toString());
                    st=new StringBuilder();
                }
                else {
                    st.append(temp.charAt(a));
                }

            }

            return (ch1*ch2)/(c*b);

    }
}
