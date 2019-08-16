package com.jpasample;

import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * コンフィグレーションクラス
 *
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	/**
	 * 一覧でのページング単位で何件表示するかの定数。
	 */
	private static int NUMBER_PER_PAGE = 10;
	
	/**
	 * ページングクラスに規定件数でページング指定を追加。
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
		resolver.setFallbackPageable(PageRequest.of(0, NUMBER_PER_PAGE));
		argumentResolvers.add(resolver);
	}

}