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
package com.tsurugidb.belayer.webapi.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.tsurugidb.belayer.webapi.dto.ErrorResult;
import com.tsurugidb.belayer.webapi.dto.SessionStatus;
import com.tsurugidb.belayer.webapi.service.SessionControlService;

import reactor.core.publisher.Mono;

@Component
public class SessionControlApiHandler {

    @Autowired
    SessionControlService sessionControlService;

    /**
     * Show Status of Tsurugi Session
     *
     * @param req Request
     * @return Response
     */
    public Mono<ServerResponse> getStatus(ServerRequest req) {
        String sessionId = req.pathVariable("session_id");
        String status = sessionControlService.getStatus(sessionId);
        return ServerResponse.ok().body(BodyInserters.fromValue(new SessionStatus(sessionId, status)));
    }

    /**
     * Kill Tsurugi Session
     *
     * @param req Request
     * @return Response
     */
    public Mono<ServerResponse> killSession(ServerRequest req) {

        String sessionId = req.pathVariable("session_id");
        boolean success = sessionControlService.killSession(sessionId);
        if (success) {
            return ServerResponse.ok().build();
        }
        return ServerResponse.status(500)
                .bodyValue(new ErrorResult("failed to kill session."));

    }

}
