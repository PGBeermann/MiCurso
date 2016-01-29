package org.example.micurso.app;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class UsuariosSQLiteHelper extends SQLiteOpenHelper {

    // Database Name
    private static final String DATABASE_NAME = "DBUsuarios";

    // Contacts table name
    private static final String TABLE_CONTACTS = "Usuarios";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_ABREV = "abrev";
    private static final String KEY_WEBSITE = "website";



    //Sentencia SQL para crear la tabla de Usuarios
    String sqlCreate = "CREATE TABLE Usuarios (ID INTEGER PRIMARY KEY,website TEXT, abrev TEXT)";

    public UsuariosSQLiteHelper(Context contexto, String nombre,CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        //NOTA: Por simplicidad del ejemplo aquí utilizamos directamente la opción de
        //      eliminar la tabla anterior y crearla de nuevo vacía con el nuevo formato.
        //      Sin embargo lo normal será que haya que migrar datos de la tabla antigua
        //      a la nueva, por lo que este método debería ser más elaborado.

        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Usuarios");

        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreate);
    }

    public myCurso getUsuario(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("Usuarios", new String[] { "ID","website", "abrev" }, "id" + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        myCurso usuario = new myCurso(Integer.parseInt(cursor.getString(0)),cursor.getString(1), cursor.getString(2));

        return usuario;
    }

    public void eliminaCurso(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Usuarios", "id" + " = ?", new String[] { String.valueOf(id) });
        db.close();
    }

}