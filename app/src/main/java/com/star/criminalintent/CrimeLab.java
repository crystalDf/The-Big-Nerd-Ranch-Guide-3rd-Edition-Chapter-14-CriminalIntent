package com.star.criminalintent;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.star.criminalintent.database.CrimeBaseHelper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CrimeLab {

    private static CrimeLab sCrimeLab;

    private Map<UUID, Crime> mCrimes;

    private Context mContext;
    private SQLiteDatabase mSQLiteDatabase;

    private CrimeLab(Context context) {
        mContext = context.getApplicationContext();
        mSQLiteDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();

        mCrimes = new LinkedHashMap<>();
    }

    public static CrimeLab getInstance(Context context) {
        if (sCrimeLab == null) {
            synchronized (CrimeLab.class) {
                if (sCrimeLab == null) {
                    sCrimeLab = new CrimeLab(context);
                }
            }
        }
        return sCrimeLab;
    }

    public List<Crime> getCrimes() {
        return new ArrayList<>(mCrimes.values());
    }

    public Crime getCrime(UUID id) {
        return mCrimes.get(id);
    }

    public void addCrime(Crime crime) {
        mCrimes.put(crime.getId(), crime);
    }

    public void removeCrime(Crime crime) {
        mCrimes.remove(crime.getId());
    }
}
