package com.bs.spring.board.model.vo;

import java.sql.Date;
import java.util.List;

import com.bs.spring.member.vo.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Attachment {
	private int attachmentNo;
	private Board boardNo;
	private String originalFilename;
	private String renamedFilename;
	private Date uploadDate;
	private int downloadCount;
	
}
