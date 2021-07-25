package com.dongom.springbatch.dnc;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class DncPrintItemWriter implements ItemWriter<Dnc>  {

	@Override
	public void write(List<? extends Dnc> items) throws Exception {
		for (Dnc item : items) {
			log.info(item.getCusNo());
		}
	}
}
