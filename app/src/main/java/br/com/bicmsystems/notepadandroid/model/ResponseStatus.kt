package br.com.bicmsystems.notepadandroid.model

import android.os.Parcel
import android.os.Parcelable

data class ResponseStatus(
        var sucesso: Boolean = false,
        var mensagem: String = ""
) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readByte() != 0.toByte(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (sucesso) 1 else 0)
        parcel.writeString(mensagem)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ResponseStatus> {
        override fun createFromParcel(parcel: Parcel): ResponseStatus {
            return ResponseStatus(parcel)
        }

        override fun newArray(size: Int): Array<ResponseStatus?> {
            return arrayOfNulls(size)
        }
    }

}