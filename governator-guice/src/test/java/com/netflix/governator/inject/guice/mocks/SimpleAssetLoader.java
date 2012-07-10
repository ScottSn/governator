/*
 * Copyright 2012 Netflix, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.netflix.governator.inject.guice.mocks;

import com.netflix.governator.assets.AssetLoader;
import com.netflix.governator.inject.AutoBindSingleton;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.atomic.AtomicInteger;

@AutoBindSingleton
public class SimpleAssetLoader implements AssetLoader
{
    public final AtomicInteger      setupCount = new AtomicInteger(0);
    public final AtomicInteger      tearDownCount = new AtomicInteger(0);
    public static final AtomicInteger      loadAssetCount = new AtomicInteger(0);
    public static final AtomicInteger      unloadAssetCount = new AtomicInteger(0);

    @PostConstruct
    public void     setup()
    {
        loadAssetCount.set(0);
        unloadAssetCount.set(0);
        setupCount.incrementAndGet();
    }

    @PreDestroy
    public void     tearDown()
    {
        tearDownCount.incrementAndGet();
    }

    @Override
    public void loadAsset(String name, String[] args) throws Exception
    {
        loadAssetCount.incrementAndGet();
    }

    @Override
    public void unloadAsset(String name, String[] args) throws Exception
    {
        unloadAssetCount.incrementAndGet();
    }
}
