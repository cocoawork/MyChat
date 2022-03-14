package top.cocoawork.wechat.server.protocol;


public class DataPackageWrap<E extends DataPackage> {

    private E data;

    public DataPackageWrap(E data) {
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
