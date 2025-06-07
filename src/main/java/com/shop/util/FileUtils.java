package com.shop.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.shop.attach.vo.AttachVO;
import com.shop.exception.AttachFileException;

@Component
public class FileUtils {

    /** 오늘 날짜 */
    private final String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));

    /** 업로드 경로 */
    // private final String uploadPath = Paths.get("C:", "develop", "upload",
    // today).toString();

    // 프로젝트에 수동으로 경로설정 *추후 수정필요*
    // C:\Users\audwls\Desktop\개발\shop\shop\src\main\resources\static\attach
    /*
     * private final String uploadPath = Paths.get("C:", "Users", "audwls",
     * "Desktop", "개발", "shop", "shop", "src", "main", "resources", "static",
     * "attach").toString();
     */
    private final String uploadPath = Paths.get("webapps", "tshop", "WEB-INF", "classes", "static", "attach")
            .toString();

    /**
     * 서버에 생성할 파일명을 처리할 랜덤 문자열 반환
     * 
     * @return 랜덤 문자열
     */
    private final String getRandomString() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 서버에 첨부 파일을 생성하고, 업로드 파일 목록 반환
     * 
     * @param files         - 파일 Array
     * @param productNumber - 게시글 번호
     * @return 업로드 파일 목록
     */
    public List<AttachVO> uploadFiles(MultipartFile[] files, Integer Number) {

        /* 파일이 비어있으면 비어있는 리스트 반환 */
        if (files[0].getSize() < 1) {
            return Collections.emptyList();
        }

        /* 업로드 파일 정보를 담을 비어있는 리스트 */
        List<AttachVO> attachList = new ArrayList<>();

        /* uploadPath에 해당하는 디렉터리가 존재하지 않으면, 부모 디렉터리를 포함한 모든 디렉터리를 생성 */
        File dir = new File(uploadPath);
        if (dir.exists() == false) {
            dir.mkdirs();
        }

        /* 파일 개수만큼 forEach 실행 */
        String staticPath = "/attach/"; // 파일 경로 수동으로 삽입 *추후 수정 필요!!*

        try {
            /* Files[0]:썸네일 Files[1]:내용 */

            /* 파일 확장자 */
            final String extension0 = FilenameUtils.getExtension(files[0].getOriginalFilename());
            final String extension1 = FilenameUtils.getExtension(files[1].getOriginalFilename());

            /* 서버에 저장할 파일명 (랜덤 문자열 + 확장자) */
            final String saveName0 = getRandomString() + "." + extension0;
            final String saveName1 = getRandomString() + "." + extension1;

            /* 업로드 경로에 saveName과 동일한 이름을 가진 파일 생성 */
            File target = new File(uploadPath, saveName0);
            files[0].transferTo(target);
            File target1 = new File(uploadPath, saveName1);
            files[1].transferTo(target1);

            /* 파일 정보 저장 */
            AttachVO attach = new AttachVO();
            attach.setProductNumber(Number);
            attach.setPostNumber(Number);

            // 썸네일
            attach.setAttachThumbnailOriginalName(files[0].getOriginalFilename());
            attach.setAttachThumbnailSaveName(saveName0);
            attach.setAttachThumbnailSize(files[0].getSize());
            attach.setAttachThumbnailLocation(staticPath + saveName0);

            // 내용
            if (files[1].getSize() < 1) {
                attach.setAttachContentsOriginalName(null);
                attach.setAttachContentsSaveName(null);
                attach.setAttachContentsSize(0);
                attach.setAttachContentsLocation(null);
            } else {
                attach.setAttachContentsOriginalName(files[1].getOriginalFilename());
                attach.setAttachContentsSaveName(saveName1);
                attach.setAttachContentsSize(files[1].getSize());
                attach.setAttachContentsLocation(staticPath + saveName1);
            }

            /* 파일 정보 추가 */
            attachList.add(attach);

        } catch (IOException e) {
            throw new AttachFileException("[" + files[0].getOriginalFilename() + "] failed to save file...");

        } catch (Exception e) {
            throw new AttachFileException("[" + files[0].getOriginalFilename() + "] failed to save file...");
        }

        return attachList;
    }

}