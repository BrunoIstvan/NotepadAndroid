package br.com.bicmsystems.notepadandroid.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class Nota(
        var id: String?,
        var titulo: String? = "",
        var descricao: String? = "",
        var data: String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(titulo)
        parcel.writeString(descricao)
        parcel.writeString(data)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Nota> {
        override fun createFromParcel(parcel: Parcel): Nota {
            return Nota(parcel)
        }

        override fun newArray(size: Int): Array<Nota?> {
            return arrayOfNulls(size)
        }
    }


}