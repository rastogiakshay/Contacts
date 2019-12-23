package com.facebook.contacts.data;

import android.provider.BaseColumns;

public final class ContactContract {

    private ContactContract(){}

    public static final class contactEntry implements BaseColumns {

        public static final String TABLE_NAME = "contacts";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_FIRST_NAME = "FirstName";
        public static final String COLUMN_lAST_NAME = "LastName";
        public static final String COLUMN_EMAIL = "Email";
        public static final String COLUMN_MOB_NUM = "MobNum";
    }
}
