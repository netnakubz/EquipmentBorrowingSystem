package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.PostBorrowModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PostBorrowRepository extends PagingAndSortingRepository<PostBorrowModel, Integer> {

    @Query(value = "SELECT * FROM post_borrow WHERE details like %:query%",
            nativeQuery = true)
    Iterable<PostBorrowModel> search(@Param("query") String query);

    Iterable<PostBorrowModel> searchAllByDetailsIsContainingOrDetailsLike(String a,String b);
}
