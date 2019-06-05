package com.spsy.common.utils;


public class FastDFSUtil {

    private String ext;

    private String length;

    /**文件名称*/
    private String name;

    /**文件内容*/
    private byte[] content;

    public FastDFSUtil(String ext, String length, String name, byte[] content) {
        this.ext = ext;
        this.length = length;
        this.name = name;
        this.content = content;
    }

    public String getExt() {
        return this.ext;
    }

    public String getLength() {
        return this.length;
    }

    public String getName() {
        return this.name;
    }

    public byte[] getContent() {
        return this.content;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof FastDFSUtil)) return false;
        final FastDFSUtil other = (FastDFSUtil) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$ext = this.getExt();
        final Object other$ext = other.getExt();
        if (this$ext == null ? other$ext != null : !this$ext.equals(other$ext)) return false;
        final Object this$length = this.getLength();
        final Object other$length = other.getLength();
        if (this$length == null ? other$length != null : !this$length.equals(other$length)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        if (!java.util.Arrays.equals(this.getContent(), other.getContent())) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $ext = this.getExt();
        result = result * PRIME + ($ext == null ? 43 : $ext.hashCode());
        final Object $length = this.getLength();
        result = result * PRIME + ($length == null ? 43 : $length.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        result = result * PRIME + java.util.Arrays.hashCode(this.getContent());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof FastDFSUtil;
    }

    public String toString() {
        return "FastDfsFile(ext=" + this.getExt() + ", length=" + this.getLength() + ", name=" + this.getName() + ", content=" + java.util.Arrays.toString(this.getContent()) + ")";
    }
}
