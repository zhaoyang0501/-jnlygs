package xwgl.common.util;
/*package cmcc.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.hsae.tsp.carfun.common.Constants;
import com.hsae.tsp.carfun.vfc.api.CircleApi;
import com.hsae.tsp.core.exception.ServiceException;

*//**
 * 消息文件工具类
 * 
 * @author shishun
 *
 *//*
@Component
public class MsgFileUtil {
    private static final Logger LOG = LoggerFactory.getLogger(MsgFileUtil.class);
    private Random random = new Random();
    private static final String FILE_PATH_SEPARATOR = "/";// 文件路径分隔符
    private static final String AVATAR_IMG_FOLDER = "resources/avatar";// 存放头像文件夹
    private static final String MSG_IMG_FOLDER = "resources/msg/img";// 存放图片消息文件夹
    private static final String MSG_VIDEO_FOLDER = "resources/msg/video";// 存放视频消息文件夹

    // 图片文件后缀名
    private static final String[] IMGS_EXT = { ".png", ".jpg", ".jpeg", ".gif", ".bmp" };
    private static final long IMG_MAX_SIZE = 10485760L; // 图片文件最大10M
    private static final long VOICE_MAX_SIZE = 15728640L; // 语音文件最大15M
    private static final long VIDEO_MAX_SIZE = 104857600L; // 视频文件最大100M

    private static final long MB = 1048576L; // 1MB 等于1024*1024B

    @Autowired
    private HttpServletRequest request;
    // web服务基本路径
    @Value("${system.basepath}")
    private String basePath;

    // 需要生成快照缩略图最小图片字节数
    @Value("${system.snapshot.img.size}")
    private long imgSnapshotSize;
    // 图片快照缩略图宽度
    @Value("${system.snapshot.img.width}")
    private int imgWidth;
    // 图片快照缩略图宽度质量(0-1)
    @Value("${system.snapshot.img.quality}")
    private double imgQuality;

    // ffmpeg工具路径
    @Value("${system.ffmpegPath}")
    private String ffmpegPath;
    // 视频截图的尺寸
    @Value("${system.screenshot.dimension}")
    private String dimension;
    // 文件系统存储目录
    @Value("${system.filesysname}")
    private String filesysname;

    *//**
     * 生成随机数字符串
     * 
     * @param len 随机数字符串长度
     * @return 随机数字符串
     *//*
    private String generateRandomNum(int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    *//**
     * 生成文件夹
     * 
     * @param folderPath 文件夹
     * @return 文件夹地址
     *//*
    public String generateFolder(String folderPath) {
        StringBuilder sb = new StringBuilder(folderPath);
        sb.append(FILE_PATH_SEPARATOR);
        // 按天区分
        sb.append(DateFormatUtils.format(new Date(), Constants.CARFUN_DATE_FORMAT));
        sb.append(FILE_PATH_SEPARATOR);
        // 随机4位数字的中间目录
        sb.append(generateRandomNum(4));
        sb.append(FILE_PATH_SEPARATOR);
        return sb.toString();
    }

    *//**
     * 生成文件名
     * 
     * @param fileExt 文件类型
     * @return 文件名
     *//*
    public String generateFileName(String fileExt) {
        StringBuilder sb = new StringBuilder();
        String ts = String.valueOf(System.currentTimeMillis());
        // 文件名为当前时间戳的后9位(已按天区分)+4为随机数
        sb.append(ts.substring(ts.length() - 9));
        sb.append(generateRandomNum(4));
        sb.append(Constants.DOT);
        sb.append(fileExt);
        return sb.toString();
    }

    *//**
     * 生成文件相对路径
     * 
     * @param folderPath 文件夹路径
     * @return 文件相对路径
     *//*
    private String generateFileRelativePath(String fileName, String folderPath) {
        StringBuilder sb = new StringBuilder();
        // 文件后缀名
        String fileExt = StringUtils.substringAfterLast(fileName, Constants.DOT);
        sb.append(generateFolder(folderPath));
        sb.append(generateFileName(fileExt));
        return sb.toString();
    }

    *//**
     * 
     * @param data 文件内容
     * @param folderPath 文件夹路径
     * @param imagetype 文件类型
     * @return
     *//*
    private String uploadFile(byte[] data, String folderPath, String imagetype) {
        try {
            StringBuilder sb = new StringBuilder();
            String folder = generateFolder(folderPath);
            String fileName = generateFileName(imagetype);
            String absolutePath = generateAbsolutePath(folder);
            File file = new File(absolutePath, fileName);
            FileUtils.writeByteArrayToFile(file, data);
            sb.append(folder);
            sb.append(fileName);
            return sb.toString();
        } catch (Exception e) {
            throw new ServiceException("保存文件异常", e);
        }
    }

    *//**
     * 上传文件
     * 
     * @param file 文件
     * @param folderPath 保存文件的文件夹路径
     * @return 文件相对位置
     *//*
    private String uploadFile(MultipartFile file, String folderPath) {
        try {
            String relativePath = generateFileRelativePath(file.getOriginalFilename(), folderPath);
            String absolutePath = generateAbsolutePath(relativePath);
            File f = new File(absolutePath);
            FileUtils.copyInputStreamToFile(file.getInputStream(), f);
            return relativePath;
        } catch (Exception e) {
            throw new ServiceException("保存文件异常", e);
        }
    }

    *//**
     * 获取图片文件支持的格式
     * 
     * @return 图片文件支持的格式,以英文逗号分隔
     *//*
    private String getImgFileExts() {
        return StringUtils.join(IMGS_EXT, Constants.COMMA);
    }

    *//**
     * 生成文件web访问路径
     * 
     * @param relativePath 文件相对路径
     * @return 文件web访问路径
     *//*
    public String generateWebPath(String relativePath) {
        return getBasePath() + relativePath;
    }

    *//**
     * 生成绝对路径
     * 
     * @param relativePath 相对路径
     * @return 绝对路径
     *//*
    public String generateAbsolutePath(String relativePath) {
        String realpath=request.getServletContext().getRealPath(FILE_PATH_SEPARATOR);
        String realpath2=request.getSession().getServletContext().getRealPath(FILE_PATH_SEPARATOR);
        String contextPath=request.getContextPath();
        String projectAbsolutePhysicsPath = StringUtils.substringBeforeLast(request.getServletContext().getRealPath(FILE_PATH_SEPARATOR),StringUtils.substringAfter(request.getContextPath(), FILE_PATH_SEPARATOR));
        LOG.error("====realpath:"+realpath);
        LOG.error("===="+realpath2);
        LOG.error("====contextPath:"+contextPath);
        LOG.error("====projectAbsolutePhysicsPath:"+projectAbsolutePhysicsPath);
        StringBuilder sb = new StringBuilder(projectAbsolutePhysicsPath);
        sb.append(FILE_PATH_SEPARATOR);
        sb.append(filesysname);
        sb.append(relativePath);
        LOG.error("====generateAbsolutePath:"+ sb.toString());
        return sb.toString();
    }

    *//**
     * Web项目基本路径
     * 
     * @return the basePath
     *//*
    public String getBasePath() {
        return basePath + filesysname;
    }

    *//**
     * 校验图片文件后缀名
     * 
     * @param fileName 图片文件名称
     *//*
    public void checkImgFileExt(String fileName) {
        boolean isImg = false;
        for (String imgExt : IMGS_EXT) {
            if (StringUtils.endsWithIgnoreCase(fileName, imgExt)) {
                isImg = true;
                break;
            }
        }
        if (!isImg) {
            throw new ServiceException("图片只能为以下格式：" + getImgFileExts());
        }
    }

    *//**
     * 校验图片文件大小
     * 
     * @param size 图片文件字节数
     *//*
    public void checkImgFileSize(long size) {
        if (!checkFileSize(size, IMG_MAX_SIZE)) {
            throw new ServiceException("图片文件大小超出上限,最大为" + IMG_MAX_SIZE / MB + "MB");
        }
    }

    *//**
     * 校验语音文件大小
     * 
     * @param size 语音文件字节数
     *//*
    public void checkVoiceFileSize(long size) {
        if (!checkFileSize(size, VOICE_MAX_SIZE)) {
            throw new ServiceException("语音文件大小超出上限,最大为" + VOICE_MAX_SIZE / MB + "MB");
        }
    }

    *//**
     * 校验视频文件大小
     * 
     * @param size 视频文件字节数
     *//*
    public void checkVideoFileSize(long size) {
        if (!checkFileSize(size, VIDEO_MAX_SIZE)) {
            throw new ServiceException("视频文件大小超出上限,最大为" + VIDEO_MAX_SIZE / MB + "MB");
        }
    }

    *//**
     * 校验文件大小
     * 
     * @param size 当前文件字节数
     * @param maxSize 文件最大字节数
     * @return boolean
     *//*
    public boolean checkFileSize(Long size, Long maxSize) {
        return maxSize >= size;
    }

    *//**
     * 上传头像文件
     * 
     * @param data 图片内容
     * @param imagetype 图片类型
     * @return 图片文件相对路径
     *//*
    public String uploadImgFile(byte[] data, String imagetype) {
        return uploadFile(data, AVATAR_IMG_FOLDER, imagetype);
    }

    *//**
     * 上传图片文件
     * 
     * @param file 图片文件
     * @return 图片文件相对路径
     *//*
    public String uploadImgFile(MultipartFile file) {
        return uploadFile(file, MSG_IMG_FOLDER);
    }

    *//**
     * 上传视频文件
     * 
     * @param file 视频文件
     * @return 视频文件相对路径
     *//*
    public String uploadVideoFile(MultipartFile file) {
        return uploadFile(file, MSG_VIDEO_FOLDER);
    }

    *//**
     * 创建图片快照
     * 
     * @param imgRelativePath 图片文件相对路径
     * @return 图片快照相对路径
     *//*
    public String createImgSnapshot(String imgRelativePath) {
        // 如果异常，则快照即为原始图片
        String snapShotImgPath = imgRelativePath;
        File file = null;
        try {
            String absolutePath = generateAbsolutePath(imgRelativePath);
            file = new File(absolutePath);
            checkImgFileExt(file.getName());
            if (file.exists() && (file.length() > imgSnapshotSize)) {
                String snapshotFilePath = absolutePath.substring(0, absolutePath.lastIndexOf(Constants.DOT));
                snapshotFilePath = snapshotFilePath + "_ss";
                Thumbnails.of(file).width(imgWidth).outputQuality(imgQuality).outputFormat("jpg")
                        .toFile(snapshotFilePath);
                snapShotImgPath = imgRelativePath.substring(0, imgRelativePath.lastIndexOf(Constants.DOT)) + "_ss.jpg";
            }
        } catch (Exception e) {
            file = null;
            throw new ServiceException("生成图片缩略图异常", e);
        }
        return snapShotImgPath;
    }

    *//**
     * 从路径中获取文件名
     * 
     * @param path 文件路径
     * @return 文件名
     *//*
    public String findFileNameByPath(String path) {
        return StringUtils.substringAfterLast(path, FILE_PATH_SEPARATOR);
    }

    *//**
     * 视频截图
     * 
     * @param upFilePath 用于指定要转换格式的文件,要截图的视频源文件
     * @return 截图的相对地址
     *//*
    public String createVideoSnapshot(String upFilePath) {
        String absolutePath = generateAbsolutePath(upFilePath);
        String fileName = StringUtils.substringBeforeLast(absolutePath, Constants.DOT);
        String screenshotFile = fileName + "_screenshot.jpg";
        // 创建一个List集合来保存从视频中截取图片的命令
        List<String> cutpic = new ArrayList<String>();
        cutpic.add(ffmpegPath);
        cutpic.add("-i");
        cutpic.add(absolutePath); // 同上
        cutpic.add("-y");
        cutpic.add("-f");
        cutpic.add("image2");
        cutpic.add("-ss"); // 添加参数＂-ss＂，该参数指定截取的起始时间
        cutpic.add("1"); // 添加起始时间为第1秒
        cutpic.add("-t"); // 添加参数＂-t＂，该参数指定持续时间
        cutpic.add("0.001"); // 添加持续时间为1毫秒
        cutpic.add("-s"); // 添加参数＂-s＂，该参数指定截取的图片大小
        cutpic.add(dimension); // 添加截取的图片大小为350*240
        cutpic.add(screenshotFile); // 添加截取的图片的保存路径
        ProcessBuilder builder = new ProcessBuilder();
        try {
            builder.command(cutpic);
            builder.redirectErrorStream(true);
            // 如果此属性为 true，则任何由通过此对象的 start() 方法启动的后续子进程生成的错误输出都将与标准输出合并，
            // 因此两者均可使用 Process.getInputStream() 方法读取。这使得关联错误消息和相应的输出变得更容易
            builder.start();
        } catch (Exception e) {
            throw new ServiceException("生成视频截图异常", e);
        }
        return StringUtils.substringBeforeLast(upFilePath, Constants.DOT) + "_screenshot.jpg";
    }
}
*/