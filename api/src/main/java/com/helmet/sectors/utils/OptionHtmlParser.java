package com.helmet.sectors.utils;

import com.helmet.sectors.models.entity.Sector;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Parse html to list sectors by optional tag
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OptionParserHtml {

	/**
	 * @return List of sectors
	 */
	@SneakyThrows
	public static List<Sector> read(String nameWithPath) {

		Resource resource = new ClassPathResource(nameWithPath);
		InputStream inputStream = resource.getInputStream();
		Reader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

		StringBuilder textBuilder = new StringBuilder();

		int read = 0;
		while ((read = reader.read()) != -1) {
			textBuilder.append((char) read);
		}

		String text = textBuilder.toString();

		List<String> option = Arrays.stream(text.split("\n"))
				.map(s -> s.contains("option") ? s : null)
				.filter(Objects::nonNull)
				.map(OptionParserHtml::getOptionTextWithLevel)
				.collect(Collectors.toList());

		return createSectors(option);
	}


	/**
	 * Convert list raw strings to list Sectors
	 *
	 * @param strings strings
	 * @return list sectors
	 */
	private static List<Sector> createSectors(List<String> strings) {

		String spaceLevel = "&nbsp;&nbsp;&nbsp;&nbsp;";

		List<Sector> result = new ArrayList<>();
		if (!strings.isEmpty()) {

			LinkedList<Sector> sectorsStack = new LinkedList<>();
			AtomicInteger level = new AtomicInteger(0);
			AtomicReference<Sector> previousSector = new AtomicReference<>();
			strings.forEach(s -> {

				int levelCurrent = StringUtils.countOccurrencesOf(s, spaceLevel);
				String sectorName = s.replace(spaceLevel.repeat(levelCurrent), "");

				Sector currentSector = new Sector();
				currentSector.setId(UUID.randomUUID());
				currentSector.setSector(sectorName);

				// resolve hierarchy level
				if (levelCurrent == level.get()) {
					Sector peekedSector = sectorsStack.peek();
					if (Objects.nonNull(peekedSector)) {
						currentSector.setParentId(peekedSector.getId());
						peekedSector.getSectors().add(currentSector);
					} else {
						result.add(currentSector);
					}
					previousSector.set(currentSector);
				} else if (levelCurrent > level.get()) {
					sectorsStack.push(previousSector.get());
					Sector lastFromStack = sectorsStack.getFirst();
					currentSector.setParentId(lastFromStack.getId());
					lastFromStack.getSectors().add(currentSector);
					previousSector.set(currentSector);
					level.set(levelCurrent);
				} else if (levelCurrent < level.get()) {
					int actualLevel = level.get() - levelCurrent;
					IntStream.range(0, actualLevel).forEach((i) -> sectorsStack.removeFirst());
					Sector peekedSector = sectorsStack.peek();
					if (Objects.nonNull(peekedSector)) {
						currentSector.setParentId(peekedSector.getId());
						peekedSector.getSectors().add(currentSector);
					} else {
						result.add(currentSector);
					}
					previousSector.set(currentSector);
					level.set(levelCurrent);
				}
			});
		}
		return result;
	}

	/**
	 * Process raw string
	 *
	 * @param s raw string
	 * @return optional name with level
	 */
	private static String getOptionTextWithLevel(String s) {

		s = s.substring(s.indexOf(">") + 1);
		s = s.substring(0, s.indexOf("<"));
		return s;
	}
}
