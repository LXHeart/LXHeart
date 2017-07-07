package com.lxh.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;

/**
 * Java自带的API对FTP的操作
 * jdk1.7以上专用
 * 
 */
public class FtpUtil17_a{
	
	private static final Log log = LogFactory.getLog(FtpUtil17_a.class);
	
	private String localfilename;
	private String remotefilename;
	
	private String username;
	private String password;
	private String ip;
	private String path="";
	private int port=0;
	public static Long totalSize  = 1L;
	public static Long tempSize = 0L;
	
	private static String localPath = "";
	private static String targetName = "";
	private static InputStream is = null;
	
	
	private FtpClient ftpClient;
	
	public FtpUtil17_a(String ip, String username,
			String password) {
		this.username=username;
		this.ip=ip;
		this.password=password;
		
		connectServer();
	}
	
	public FtpUtil17_a(InputStream is, String targetName, long totalSize){
		FtpUtil17_a.is = is;
		FtpUtil17_a.targetName = targetName;
		FtpUtil17_a.totalSize = totalSize;
		
	}
	
	public FtpUtil17_a(String ip, String username,
			String password,String path) {
		this.username=username;
		this.ip=ip;
		this.password=password;
		this.path=path;
		
		connectServer();
	}

	/**
	 * 连接FTP服务器
	 * 
	 * @param ip
	 * @param port
	 * @param username
	 * @param password
	 * @param path
	 */
	public void connectServer() {
		try {
			/* ******连接服务器的两种方法****** */
			ftpClient = FtpClient.create();
			try {
				SocketAddress addr = new InetSocketAddress(ip, port);
				ftpClient.connect(addr);
				ftpClient.login(username, password.toCharArray());
				System.out.println("ftp login success!");
				if (!"".equals(path)||path.length() != 0) {
					// 把远程系统上的目录切换到参数path所指定的目录
					ftpClient.changeDirectory(path);
				}
				ftpClient.setBinaryType();
			} catch (FtpProtocolException e) {
				e.printStackTrace();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	/**
	 * 关闭连接
	 */
	public void closeConnect() {
		try {
			ftpClient.close();
			System.out.println("disconnect success");
		} catch (IOException ex) {
			System.out.println("not disconnect");
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	/**
	 * 上传文件
	 * 
	 * @param localFile
	 *            本地文件
	 * @param remoteFile
	 *            远程文件
	 */
	public void upload(InputStream is, String remoteFile) {
		
		log.info(".......start upload!");
//		this.localfilename = localFile;
		this.remotefilename = remoteFile;
		OutputStream os = null;
//		InputStream is = null;
		try {
			// 将远程文件加入输出流中
			try {
				os = ftpClient.putFileStream(this.remotefilename);
			} catch (FtpProtocolException e) {
				e.printStackTrace();
			} // 获取本地文件的输入流
			// 创建一个缓冲区
			byte[] bytes = new byte[1024];
			int c;
			while ((c = is.read(bytes)) != -1) {
				os.write(bytes, 0, c);
				log.info("...........tempSize: " + tempSize);
				tempSize = tempSize + c;
			}
			log.info(".......upload success!");
			System.out.println("upload success");
		} catch (IOException ex) {
			System.out.println("not upload");
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (os != null) {
						os.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 下载文件
	 * 
	 * @param remoteFile
	 *            远程文件路径(服务器端)
	 * @param localFile
	 *            本地文件路径(客户端)
	 */
	public void download(String remoteFile, String localFile) {
		//TODO 这个方法有BUG
		InputStream is = null;
		FileOutputStream os = null;
		try {
			// 获取远程机器上的文件filename，借助TelnetInputStream把该文件传送到本地。
			try {
				is = ftpClient.getFileStream(remoteFile);
			} catch (FtpProtocolException e) {
				e.printStackTrace();
			}
			File file_in = new File(localFile);
			os = new FileOutputStream(file_in);
			byte[] bytes = new byte[1024];
			int c;
			while ((c = is.read(bytes)) != -1) {
				os.write(bytes, 0, c);
			}
			System.out.println("download success");
		} catch (IOException ex) {
			System.out.println("not download");
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (os != null) {
						os.flush();
						os.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 切换目录
	 * @param ftp
	 * @param path
	 */
	public static void changeDirectory(FtpClient ftp,String path) {
		try {
			ftp.changeDirectory(path);
			System.out.println(ftp.getWorkingDirectory());
		} catch (FtpProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	 /**
     * 检查文件夹是否存在
     * 
     * @param dir
     * @param ftpClient
     * @return
     */
    private Boolean isDirExist(String dir) {
        try {
            ftpClient.changeDirectory(dir);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    /**
     * 创建文件夹
     * 
     * @param dir
     * @param ftpClient
     * @throws Exception
     */
    private void createDir(String dir) throws Exception {
        ftpClient.setAsciiType();
        StringTokenizer s = new StringTokenizer(dir, "/"); // sign
        s.countTokens();
        String pathName = "";
        while (s.hasMoreElements()) {
            pathName = pathName + "/" + (String) s.nextElement();
            try {
                ftpClient.makeDirectory(pathName);
            } catch (Exception e) {
                e = null;
            }
        }
        ftpClient.setBinaryType();
 
    }
    
    /**
     * 删除FTP制定目录下的文件
     * @param filePath 文件在FTP存储的路径
     * @param fileName 要删除的文件名称
     * @return 是否删除成功
     */
    public boolean deleteFileFtp(String filePath, String fileName){  
        try{
            if (!isDirExist(filePath.replace("\\", "/"))) {
                return false;
            }
            ftpClient.changeDirectory(filePath.replace("\\", "/"));
            ftpClient.deleteFile(fileName);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
        }
    }

    
    
    
    public void uoload(){
    	log.info("......................start ProgressThread");
    	ProgressThread b = new ProgressThread();
    	b.start();
    	FtpUtil17_a fu = new FtpUtil17_a("192.168.198.1", "Administrator", "123456",
				"/");
    	fu.upload(this.is,targetName);
    }
    
   
    
	public static void main(String agrs[]) {
		File f = new File("C:/Users/Administrator.SC-201706091102/Pictures/Downloads.rar");
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		FtpUtil17_a a = new FtpUtil17_a(fis,"nihao.png",171555225);
		a.uoload();
//		a.start();
		
//		String filepath[] = {
//				"hudson/abc/online.tar.gz",
//				"hudson/abc/forqa.tar.gz" };
//		String localfilepath[] = { "/opt/1.tar.gz", "/opt/2.tar.gz" };
//		FtpUtil17 fu = new FtpUtil17("127.0.0.1", "Administrator", "123456",
//				"/");
		
		
		// 下载
//		for (int i = 0; i < filepath.length; i++) {
//			fu.download(filepath[i], localfilepath[i]);
//		}
//		fu.closeConnect();
//		fu.upload("C:/Users/Administrator.SC-201706091102/Pictures/360se8.1.1.406.exe","nihao.png");
//		while(tempSize < totalSize){
//			System.out.println(FtpUtil17.tempSize*100/FtpUtil17.totalSize);
//		}
		
	}
}