package com.example.foodtrackerapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Contract for the food database, which stores food items 
 * along with their nutritional facts and RFID tag number.
 * @author Graham
 *
 */
public final class FoodDatabaseContract {
    
    public FoodDatabaseContract() {}
    
    /*SQL data types to use for data storage */
    private static final String FLOAT_TYPE = " FLOAT";
    private static final String TEXT_TYPE = " TEXT";
    
    private static final String COMMA_SEP = ",";
    
    /*SQL command for table creation*/
    private static final String SQL_CREATE_ENTRIES = 
            "CREATE TABLE " + FoodEntry.TABLE_NAME + " (" + 
            FoodEntry._ID + " INTEGER PRIMARY KEY," +
            FoodEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_RFID_ID + TEXT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_AMOUNT_LEFT + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_SERVING_SIZE + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_TOT_CALORIES + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_FAT_CALORIES + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_CARB_CALORIES + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_PROTEIN_CALORIES + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_ALCOHOL_CALORIES + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_TOT_CARBS + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_FIBER + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_STARCH  + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_TOT_SUGAR + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_LACTOSE + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_SUCROSE + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_GLUCOSE + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_MALTOSE + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_FRUCTOSE + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_GALACTOSE + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_TOT_FAT + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_SAT_FAT + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_MONO_FAT + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_POLY_FAT + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_TRANS_FAT + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_TRANS_M_FAT + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_TRANS_P_FAT + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_OMEGA_THREE + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_OMEGA_SIX + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_TOT_PROTEIN + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_TRYPTOPHAN + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_THREONINE + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_ISOLEUCINE + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_LEUCINE + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_LYSINE + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_METHIONINE + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_CYSTINE + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_PHENYLALANINE + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_TYROSINE + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_VALINE + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_ARGININE + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_HISTADINE + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_ALANINE + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_ASPARTIC_ACID + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_GLUTAMIC_ACID + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_GLYCINE + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_PROLINE + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_SERINE + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_HYDROXYPROLINE + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_TOT_VIT_A + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_RETINOL + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_RETINOL_EQUIV + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_ALPHA_CAROTINE + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_BETA_CAROTINE + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_BETA_CRYPTOXANTHIN + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_LYCOPENE + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_LUTEIN_AND_ZEAXANTHIN + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_TOT_VIT_E + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_BETA_TOCOPHEROL + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_GAMMA_TOCOPHEROL + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_DELTA_TOCOPHEROL + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_TOT_VIT_B + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_THIAMINE + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_RIBOFLAVIN + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_NIACIN + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_PANTOTHENIC_ACID + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_PYRIDOXINE + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_BIOTIN + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_FOLIC_ACID + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_VIT_B_12 + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_VIT_K + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_VIT_C + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_VIT_D + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_CHOLINE + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_BETAINE + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_CALCIUM + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_IRON + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_MAGNESIUM + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_PHOSPHOROUS + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_POTASSIUM + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_SODIUM + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_ZINC + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_COPPER + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_MANGANESE + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_SELENIUM + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_FLUORIDE + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_CHOLESTEROL + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_TOT_PHYTOSTEROLS + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_CAMPESTEROL + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_STIGNASTIGMA + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_BETASITOSTEROL + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_ALCOHOL + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_WATER + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_ASH + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_CAFFEINE + FLOAT_TYPE + COMMA_SEP +
            FoodEntry.COLUMN_NAME_THEOBROMINE + FLOAT_TYPE + COMMA_SEP + " )";
    
    /*SQL command for table deletion*/
    private static final String SQL_DELETE_ENTRIES = 
            "DROP TABLE IF EXISTS " + FoodEntry.TABLE_NAME;
    
    /*Inner class for database helper*/
    public class FoodDatabaseHelper extends SQLiteOpenHelper {
        
        /*If the database schema is changed, the database version number must be incremented*/
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "FoodData.db";
        
        public FoodDatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }
        
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            /*When changes are made to the database schema, 
             * logic must be inserted here to specify which fields to 
             * add to/drop from records in the database in order to upgrade it.*/
        }
        
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            /*When changes are made to the database schema, 
             * logic must be inserted here to specify which fields to 
             * add to/drop from records in the database in order to downgrade it.*/
        }
    }
            
    /* Inner class to define table contents */
    public static abstract class FoodEntry implements BaseColumns {
        public static final String TABLE_NAME = "food";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_ENTRY_ID = "entryID";
        public static final String COLUMN_NAME_RFID_ID = "rfidID";
        public static final String COLUMN_NAME_AMOUNT_LEFT = "amountLeft";
        public static final String COLUMN_NAME_SERVING_SIZE = "servingSize";
        public static final String COLUMN_NAME_TOT_CALORIES = "totalCalories"; //Total of all below items in this section
        public static final String COLUMN_NAME_FAT_CALORIES = "fatCalories";
        public static final String COLUMN_NAME_CARB_CALORIES = "carbCalories:";
        public static final String COLUMN_NAME_PROTEIN_CALORIES = "proteinCalories";
        public static final String COLUMN_NAME_ALCOHOL_CALORIES = "alcoholCalories";
        
        //Carbohydrates
        public static final String COLUMN_NAME_TOT_CARBS = "totalCarbohydrates"; //Total of all below items in this section
        public static final String COLUMN_NAME_FIBER = "fiber";
        public static final String COLUMN_NAME_STARCH = "starch";
        public static final String COLUMN_NAME_TOT_SUGAR = "totalSugar"; //Total of all below items in this section
        public static final String COLUMN_NAME_LACTOSE = "lactose";
        public static final String COLUMN_NAME_SUCROSE = "sucrose";
        public static final String COLUMN_NAME_GLUCOSE = "glucose";
        public static final String COLUMN_NAME_MALTOSE = "maltose";
        public static final String COLUMN_NAME_FRUCTOSE = "fructose";
        public static final String COLUMN_NAME_GALACTOSE = "galactose"; //All hail Galactose, the magical space cow
      
        //Fats
        public static final String COLUMN_NAME_TOT_FAT = "totalFat"; //Total of all below items in this section 
        public static final String COLUMN_NAME_SAT_FAT = "satFat";
        public static final String COLUMN_NAME_MONO_FAT = "monounsaturatedFat";
        public static final String COLUMN_NAME_POLY_FAT = "polyunsaturatedFat";
        public static final String COLUMN_NAME_TRANS_FAT = "transFattyAcids";
        public static final String COLUMN_NAME_TRANS_M_FAT = "transMonoenoicFattyAcids";
        public static final String COLUMN_NAME_TRANS_P_FAT = "transPolyenoicFattyAcids";
        public static final String COLUMN_NAME_OMEGA_THREE = "omegaThreeFattyAcids";
        public static final String COLUMN_NAME_OMEGA_SIX = "omegaSixFattyAcids";
        
        //Proteins & amino acids
        public static final String COLUMN_NAME_TOT_PROTEIN = "totalProtein"; //Total of all below items in this section
        public static final String COLUMN_NAME_TRYPTOPHAN = "tryptophan";
        public static final String COLUMN_NAME_THREONINE = "threonine";
        public static final String COLUMN_NAME_ISOLEUCINE = "isoleucine";
        public static final String COLUMN_NAME_LEUCINE = "leucine";
        public static final String COLUMN_NAME_LYSINE = "lysine";
        public static final String COLUMN_NAME_METHIONINE = "methionine";
        public static final String COLUMN_NAME_CYSTINE = "cystine";
        public static final String COLUMN_NAME_PHENYLALANINE = "phenylalanine";
        public static final String COLUMN_NAME_TYROSINE = "tyrosine";
        public static final String COLUMN_NAME_VALINE = "valine";
        public static final String COLUMN_NAME_ARGININE = "arginine";
        public static final String COLUMN_NAME_HISTADINE = "histidine";
        public static final String COLUMN_NAME_ALANINE = "alanine";
        public static final String COLUMN_NAME_ASPARTIC_ACID = "asparticAcid";
        public static final String COLUMN_NAME_GLUTAMIC_ACID = "glutamicAcid";
        public static final String COLUMN_NAME_GLYCINE = "glycine";
        public static final String COLUMN_NAME_PROLINE = "proline";
        public static final String COLUMN_NAME_SERINE = "serine";
        public static final String COLUMN_NAME_HYDROXYPROLINE = "hydroxyproline";
        
        //Vitamin A
        public static final String COLUMN_NAME_TOT_VIT_A = "totalVitaminA"; //Total of all below items in this section
        public static final String COLUMN_NAME_RETINOL = "retinol";
        public static final String COLUMN_NAME_RETINOL_EQUIV = "retinolActivityEquivalent";
        public static final String COLUMN_NAME_ALPHA_CAROTINE = "alphaCarotine";
        public static final String COLUMN_NAME_BETA_CAROTINE = "betaCarotine";
        public static final String COLUMN_NAME_BETA_CRYPTOXANTHIN = "betaCryptoxanthin";
        public static final String COLUMN_NAME_LYCOPENE = "lycopene";
        public static final String COLUMN_NAME_LUTEIN_AND_ZEAXANTHIN = "luteinAndZeaxanthin";
        
        //Vitamin E
        public static final String COLUMN_NAME_TOT_VIT_E = "totalVitaminE"; //Total of all below items in this section
        public static final String COLUMN_NAME_BETA_TOCOPHEROL = "betaTocopherol";
        public static final String COLUMN_NAME_GAMMA_TOCOPHEROL = "gammaTocopherol"; 
        public static final String COLUMN_NAME_DELTA_TOCOPHEROL = "deltaTocopherol"; 
        
        //Vitamin B
        public static final String COLUMN_NAME_TOT_VIT_B = "totalVitaminB"; //Total of all below items in this section
        public static final String COLUMN_NAME_THIAMINE = "thiamine";
        public static final String COLUMN_NAME_RIBOFLAVIN = "riboflavin";
        public static final String COLUMN_NAME_NIACIN = "niacin";
        public static final String COLUMN_NAME_PANTOTHENIC_ACID = "pantothenicAcid";
        public static final String COLUMN_NAME_PYRIDOXINE = "pyridoxine";
        public static final String COLUMN_NAME_BIOTIN = "biotin";
        public static final String COLUMN_NAME_FOLIC_ACID = "folicAcid";
        public static final String COLUMN_NAME_VIT_B_12 = "vitaminBTwelve";
        
        //Other vitamins
        public static final String COLUMN_NAME_VIT_K = "vitaminK";
        public static final String COLUMN_NAME_VIT_C = "vitaminC";
        public static final String COLUMN_NAME_VIT_D = "vitaminD";
        public static final String COLUMN_NAME_CHOLINE = "choline";
        public static final String COLUMN_NAME_BETAINE = "betaine";
        
        //Minerals
        public static final String COLUMN_NAME_CALCIUM = "calcium";
        public static final String COLUMN_NAME_IRON = "iron";
        public static final String COLUMN_NAME_MAGNESIUM = "magnesium";
        public static final String COLUMN_NAME_PHOSPHOROUS = "phosphorous";
        public static final String COLUMN_NAME_POTASSIUM = "potassium";
        public static final String COLUMN_NAME_SODIUM = "sodium";
        public static final String COLUMN_NAME_ZINC = "zinc";
        public static final String COLUMN_NAME_COPPER = "copper";
        public static final String COLUMN_NAME_MANGANESE = "manganese";
        public static final String COLUMN_NAME_SELENIUM = "selenium";
        public static final String COLUMN_NAME_FLUORIDE = "fluoride";
        
        //Setrols
        public static final String COLUMN_NAME_CHOLESTEROL = "cholesterol";
        public static final String COLUMN_NAME_TOT_PHYTOSTEROLS = "totalPhytosterols"; //Total of all below items in this section
        public static final String COLUMN_NAME_CAMPESTEROL = "campesterol";
        public static final String COLUMN_NAME_STIGNASTIGMA = "stignastigma";
        public static final String COLUMN_NAME_BETASITOSTEROL = "betasitosterol";
        
        //Other
        public static final String COLUMN_NAME_ALCOHOL = "alcohol";
        public static final String COLUMN_NAME_WATER = "water";
        public static final String COLUMN_NAME_ASH = "ash";
        public static final String COLUMN_NAME_CAFFEINE = "caffeine";
        public static final String COLUMN_NAME_THEOBROMINE = "theobromine";       
    }
}
