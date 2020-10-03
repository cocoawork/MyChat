package top.cocoawork.chat.message;


import top.cocoawork.chat.message.TransferPackage;

public class TransferPackageWrap<E extends TransferPackage> {

    private E data;

    public TransferPackageWrap(E data) {
        this.data = data;
    }

    public byte[] getBytes() {
        return data.toJsonString().getBytes();
    }

    public int getLength() {
        return getBytes().length;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

}
