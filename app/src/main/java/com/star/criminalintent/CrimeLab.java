package com.star.criminalintent;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.star.criminalintent.database.CrimeBaseHelper;
import com.star.criminalintent.database.CrimeDbSchema.CrimeTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {

    private static CrimeLab sCrimeLab;

    private Context mContext;
    private SQLiteDatabase mSQLiteDatabase;

    private CrimeLab(Context context) {
        mContext = context.getApplicationContext();
        mSQLiteDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();
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
        return new ArrayList<>();
    }

    public Crime getCrime(UUID id) {
        return null;
    }

    public void addCrime(Crime crime) {

    }

    public void removeCrime(Crime crime) {

    }

    private static ContentValues getContentValues(Crime crime) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CrimeTable.Cols.UUID, crime.getId().toString());
        contentValues.put(CrimeTable.Cols.TITLE, crime.getTitle());
        contentValues.put(CrimeTable.Cols.DATE, crime.getDate().getTime());
        contentValues.put(CrimeTable.Cols.SOLVED, crime.isSolved() ? 1 : 0);
        contentValues.put(CrimeTable.Cols.REQUIRES_POLICE, crime.isRequiresPolice() ? 1 : 0);

        return contentValues;
    }
}
