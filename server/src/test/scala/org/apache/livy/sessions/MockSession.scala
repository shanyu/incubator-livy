/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.livy.sessions

import org.apache.livy.LivyConf

class MockSession(id: Int, owner: String, conf: LivyConf, name: Option[String] = None)
  extends Session(id, name, owner, conf) {
  case class RecoveryMetadata(id: Int) extends Session.RecoveryMetadata()

  override val proxyUser = None

  override def start(): Unit = ()

  var stopped = false
  override protected def stopSession(): Unit = {
    stopped = true
  }

  override def logLines(): IndexedSeq[String] = IndexedSeq()

  override def state: SessionState = SessionState.Idle

  override def recoveryMetadata: RecoveryMetadata = RecoveryMetadata(0)
}
