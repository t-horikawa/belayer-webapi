/*
 * Copyright 2023 tsurugi project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tsurugidb.belayer.webapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tsurugidb.belayer.webapi.exec.SessionKillExec;
import com.tsurugidb.belayer.webapi.exec.SessionStatusExec;

/**
 * Session Control Service
 */
@Component
public class SessionControlService {

  @Autowired
  SessionStatusExec sessionStatusExec;

  @Autowired
  SessionKillExec sessionKillExec;

  /**
   * kill Tsurugi Session.
   *
   * @param sessionId Session ID
   * @return kill suceeded
   */
  public boolean killSession(String sessionId) {
    return sessionKillExec.killSession(sessionId);
  }

  /**
   * determine Tsurugi Session.
   *
   * @param sessionId Session ID
   * @return true if availavle.
   */
  public boolean isAvailable(String sessionId) {

    return sessionStatusExec.existsSession(sessionId);
  }

}
