package com.amirbahadoramiri.androidlearning.tools.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.amirbahadoramiri.androidlearning.newmodels.Company;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface CompanyDAO {

    @Insert
    Completable insert(Company company);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    Completable update(Company company);

    @Query("SELECT * FROM company LIMIT 1")
    Single<Company> getCompany();

}
