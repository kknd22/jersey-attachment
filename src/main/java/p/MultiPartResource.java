package p;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

@Path("/form")
public class MultiPartResource {

    @POST
    @Path("upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String upload(
            @FormDataParam("file") byte[] s,
            @FormDataParam("file2") byte[] s2,
            @FormDataParam("file") FormDataContentDisposition d,
            @FormDataParam("file2") FormDataContentDisposition d2) {
        System.out.println("---------------------------- " + d.getFileName());
        System.out.println("type: " + d.getType());
        System.out.println("length is: " + s.length);
        //System.out.println(new String(s));
        System.out.println("---------------------------- " + d2.getFileName());
        System.out.println("length is: " + s2.length);
        System.out.println(new String(s2));
        return ("----------------------------\n " + d.getFileName() + ", " + d2.getFileName());
    }



    @GET
    @Path("download")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download() {
        InputStream in = getClass().getResourceAsStream("/input.docx");
        byte[] doc = getBytes(in);

        System.out.println("---------------------------- doc length is: " + doc.length);

        return Response.ok(doc, MediaType.APPLICATION_OCTET_STREAM)
            .header("content-disposition","attachment; filename = input.docx")
            .build();
    }


    private static byte[] getBytes(InputStream is){
        int len =0;
        int size = 1024;
        byte[] buf=null;

        try {
            if (is instanceof ByteArrayInputStream) {
                size = is.available();
                buf = new byte[size];
                len = is.read(buf, 0, size);
            } else {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                buf = new byte[size];
                while ((len = is.read(buf, 0, size)) != -1)
                    bos.write(buf, 0, len);
                buf = bos.toByteArray();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return buf;
    }
}
