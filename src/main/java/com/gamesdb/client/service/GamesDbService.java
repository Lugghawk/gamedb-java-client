package com.gamesdb.client.service;


import com.gamesdb.client.model.Platform;

import java.util.List;

public interface GamesDbService {

	/**
	 * @return A list of Platforms currently on TheGamesDB.net
	 */
	List<Platform> getPlatformList();

}
